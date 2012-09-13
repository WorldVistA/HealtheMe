/**
 * Copyright (C) 2012 KRM Associates, Inc. healtheme@krminc.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.krminc.phr.api.service;

import com.krminc.phr.api.converter.UserConverter;
import com.krminc.phr.api.converter.UsersConverter;
import com.krminc.phr.api.service.util.ServiceUtils;
import com.krminc.phr.core.UserConfig;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.User;
import com.sun.jersey.api.core.ResourceContext;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Users API RESTful resource class and mapping.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class UsersResource {

    final Logger logger = LoggerFactory.getLogger(UsersResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;


    public UsersResource() {
    }


    /**
     * Get method for retrieving a collection of User instance in XML format.
     *
     * @return an instance of UsersConverter
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public UsersConverter get(
        @QueryParam("start") @DefaultValue("0") int start,
        @QueryParam("max") @DefaultValue("10") int max,
        @QueryParam("orderBy") @DefaultValue("name") String orderBy,
        @QueryParam("desc") @DefaultValue("0") int desc,
        @QueryParam("u") @DefaultValue("") String username,
        @QueryParam("e") @DefaultValue("") String name,
        @QueryParam("r") @DefaultValue("a") String roles,
        @QueryParam("area") @DefaultValue("") String area
    ) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new UsersConverter(
                    getEntities(start, max, orderBy, desc, username, name, roles, area),
                    uriInfo.getAbsolutePath(),
                    Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of User using XML as the input format.
     *
     * @param data an UserConverter entity that is deserialized from an XML stream
     * @return an instance of UserConverter
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response post(UserConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            User entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getUserId() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of UserResource used for entity navigation.
     *
     * @return an instance of UserResource
     */
    @Path("{userId}/")
    public UserResource getUserResource(@PathParam("userId") Long id) {
        UserResource resource = resourceContext.getResource(UserResource.class);
        resource.setId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of UserResource used for entity navigation.
     *
     * @return an instance of UserResource
     */
    @Path("/username/{username}/")
    public UserResource getUserResourceByUsername(@PathParam("username") String username) {
        UserResource resource = resourceContext.getResource(UserResource.class);
        resource.setUsername(username);
        return resource;
    }

    private String comma(int stringLength) {
       return stringLength == 0 ? "" : ",";
    }
    private String connector(int filterLength) {
       return filterLength == 0 ? " WHERE" : " AND";
    }

    @Path("count/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getCount(
        @QueryParam("u") @DefaultValue("") String username,
        @QueryParam("e") @DefaultValue("") String name,
        @QueryParam("r") @DefaultValue("a") String roles,
        @QueryParam("area") @DefaultValue("") String area
    ) {
        StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT u.user_id) FROM user_users u JOIN user_roles r ON u.username = r.username");
        StringBuilder filter = new StringBuilder();
        Long result = 0L;
        Boolean hasFilterClause = false;

        if (!roles.isEmpty() && !roles.equalsIgnoreCase("a")){
            StringBuilder queryRoles = new StringBuilder();
            for (String role : roles.split(",")) {
                // user manager (AKA system manager)
                if (role.equalsIgnoreCase("um") || role.equalsIgnoreCase("u")) {
                    queryRoles.append( comma(queryRoles.length())+"'"+UserConfig.ROLE_PATIENTADMIN+"'" );
                // admin
                } else if (role.equalsIgnoreCase("ad")) {
                    queryRoles.append( comma(queryRoles.length())+"'"+UserConfig.ROLE_ADMIN+"'" );
                //patient
                } else if (role.equalsIgnoreCase("p")) {
                    queryRoles.append( comma(queryRoles.length())+"'"+UserConfig.ROLE_PATIENT+"'" );
                //care coordinator aka care taker
                } else if (role.equalsIgnoreCase("cc")) {
                    queryRoles.append( comma(queryRoles.length())+"'"+UserConfig.ROLE_CARETAKER+"'" );
                }
            }
            if (queryRoles.length() != 0) {
                filter.append( connector(filter.length())+" r.role IN ("+queryRoles+")" );
            }
        }
        if ( !area.equals("admin") ) {
            //do not show admin users outside of admin area
            filter.append( connector(filter.length())+" r.role <> '" +UserConfig.ROLE_ADMIN+"'" );
            //do not show caretaker users outside of admin area
            filter.append( connector(filter.length())+" r.role <> '" +UserConfig.ROLE_CARETAKER+"'" );
        }

        logger.debug("filter {} {}", filter, filter.length());
        if (filter.length() != 0) {
            query.append(filter);
            hasFilterClause = true;
        }

        if (!username.isEmpty()){
            if (ServiceUtils.hasValidCharacters(username)) {
                if (!hasFilterClause){
                    query.append(" WHERE");
                    hasFilterClause = true;
                } else {
                    query.append(" AND");
                }
                username = "%" + username + "%";
                query.append(" u.username LIKE '" +username+"'");
                //hasUsernameFilter = true;
            } else {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }

        if (!name.isEmpty()){
            if (ServiceUtils.hasValidCharacters(name)) {
                if (!hasFilterClause){
                    query.append(" WHERE");
                    hasFilterClause = true;
                } else {
                    query.append(" AND");
                }
                name = "%" + name + "%";
                query.append(" (u.first_name LIKE '" +name+"'");
                query.append(" OR");
                query.append(" u.last_name LIKE '" +name+"')");
                //hasNameFilter = true;
            } else {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            List resultD = (List)em.createNativeQuery(query.toString()).getSingleResult();
            result = (Long)resultD.get(0);
        } finally {
            persistenceSvc.close();
        }

        JSONObject jsonResult = new JSONObject();
        try {
            jsonResult.put("count", result);
        }
        catch (JSONException ex) {
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }
        return jsonResult;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of User instances
     */
    protected Collection<User> getEntities(
            int start, int max, String orderBy, int desc,
            String username, String name, String roles, String area) {
        StringBuilder query = new StringBuilder("SELECT DISTINCT u.* FROM user_users u JOIN user_roles r ON u.username = r.username");
        StringBuilder filter = new StringBuilder();

        //  orderBy :
        //    username
        //    name
        //  desc:
        //    0 -- ascending (default)
        //    >0 -- descending
        if(start<0) start=0;
        
        Boolean hasMax = true;
        if( max < 1 ){
            hasMax = false;
            start = 0;
        }

        Boolean hasFilterClause = false;
        //Boolean hasUsernameFilter = false;
        //Boolean hasNameFilter = false;

        if (!roles.isEmpty() && !roles.equalsIgnoreCase("a")){
            StringBuilder queryRoles = new StringBuilder();
            for (String role : roles.split(",")) {
                // user manager (AKA system manager)
                if (role.equalsIgnoreCase("um") || role.equalsIgnoreCase("u")) {
                    queryRoles.append( comma(queryRoles.length())+"'"+UserConfig.ROLE_PATIENTADMIN+"'" );
                // admin
                } else if (role.equalsIgnoreCase("ad")) {
                    queryRoles.append( comma(queryRoles.length())+"'"+UserConfig.ROLE_ADMIN+"'" );
                //patient
                } else if (role.equalsIgnoreCase("p")) {
                    queryRoles.append( comma(queryRoles.length())+"'"+UserConfig.ROLE_PATIENT+"'" );
                //care coordinator aka care taker
                } else if (role.equalsIgnoreCase("cc")) {
                    queryRoles.append( comma(queryRoles.length())+"'"+UserConfig.ROLE_CARETAKER+"'" );
                }
            }
            if (queryRoles.length() != 0) {
                filter.append( connector(filter.length())+" r.role IN ("+queryRoles+")" );
            }
        }
        if ( !area.equals("admin") ) {
            //do not show admin users outside of admin area
            filter.append( connector(filter.length())+" r.role <> '" +UserConfig.ROLE_ADMIN+"'" );
            //do not show caretaker users outside of admin area
            filter.append( connector(filter.length())+" r.role <> '" +UserConfig.ROLE_CARETAKER+"'" );
        }

        logger.debug("filter {} {}", filter, filter.length());
        if (filter.length() != 0) {
            query.append(filter);
            hasFilterClause = true;
        }

        if (!username.isEmpty()){
            if (ServiceUtils.hasValidCharacters(username)) {
                if (!hasFilterClause){
                    query.append(" WHERE");
                    hasFilterClause = true;
                } else {
                    query.append(" AND");
                }
                username = "%" + username + "%";
                query.append(" u.username LIKE '" +username+"'");
                //hasUsernameFilter = true;
            } else {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }

        if (!name.isEmpty()){
            if (ServiceUtils.hasValidCharacters(name)) {
                if (!hasFilterClause){
                    query.append(" WHERE");
                    hasFilterClause = true;
                } else {
                    query.append(" AND");
                }
                name = "%" + name + "%";
                query.append(" (u.first_name LIKE '" +name+"'");
                query.append(" OR");
                query.append(" u.last_name LIKE '" +name+"')");
                //hasNameFilter = true;
            } else {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }

        Boolean wasOrdered = false;
        if(orderBy.equalsIgnoreCase("username")){
            query.append(" ORDER BY u.username");
            wasOrdered = true;
        } else if(orderBy.equalsIgnoreCase("name")){
            query.append(" ORDER BY u.last_name, u.first_name, u.middle_name");
            wasOrdered = true;
        }

        if (wasOrdered) {
            if (desc>0) {
                query.append(" DESC");
            } else {
                query.append(" ASC");
            }
        }

        EntityManager em = PersistenceService.getInstance().getEntityManager();
        //logger.error("QUERY: {}", query.toString());
        Query filterQuery = em.createNativeQuery(query.toString(), User.class);
        //filterQuery.setParameter("role", new UserRole(role));
        //filterQuery.setParameter("role",role);
        if (hasMax) filterQuery.setMaxResults(max);
        //if (hasUsernameFilter) filterQuery.setParameter("username", username);
        //if (hasNameFilter) filterQuery.setParameter("name", name.toUpperCase());

        List<User> users = (List<User>)filterQuery.setFirstResult(start).getResultList();
        //logger.debug("OUTPUT: {}", users);
        return users;

//        logger.debug("User count: {}", users.size());
//        for (User user : users) {
//            if (user.isAdmin()) {
//                logger.debug("Admin user needs to be removed: {}", user);
//                users.remove(user);
//            }
//        }
//        logger.debug("User count: {}", users.size());
//        return users;
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(User entity) {
        entity.setUserId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
    }
}
