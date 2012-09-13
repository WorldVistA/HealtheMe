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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krminc.phr.security;

import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.User;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.logging.Logger;
import java.util.logging.Level;
import com.sun.logging.LogDomains;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.naming.InitialContext;

/**
 *
 * @author cmccall
 */
public class PHRRealm extends AppservRealm {
    //the following property variables are allocated in the following fashion:
    // private static PROPERTY => default value of PROPERTY
    // private static PROPERTY_PARAM => string used to specific PROPERTY in domain.xml
    // private propertyValue => variable containing version of PROPERTY for use in code (whether default or specific in domain.xml)

    //auth type property variables
    private static String AUTH_TYPE = "phrcustomauth";
    private static String AUTH_TYPE_PARAM = "auth-type";
    private String authType = null;

    //failed attempts property variables
    private static String FAILED_ATTEMPTS = "5";
    private static String FAILED_ATTEMPTS_PARAM = "failed-attempts";
    private Integer failedAttempts = null;

    //locked role name property variables
    private static String LOCKED_ROLE = "ROLE_LOCKED";
    private static String LOCKED_ROLE_PARAM = "locked-role";
    private String lockedRole = null;

    //reset role name property variables
    private static String RESET_ROLE = "ROLE_RESET";
    private static String RESET_ROLE_PARAM = "reset-role";
    private String resetRole = null;

    //jdbc resource name property variables
    private static String JDBC_RESOURCE = "jdbc/phr";
    private static String JDBC_RESOURCE_PARAM = "jdbc-resource";
    private String jdbcResource = null;

    //logging level property variables
    private static String LOG_LEVEL = "INFO";
    private static String LOG_LEVEL_PARAM = "logging-level";
    private Level logLevel = null;

    // end properties variables

    private Connection conn = null;
    private com.sun.appserv.jdbc.DataSource ds = null;

    private static Logger _logger = null;

    static {
        _logger = Logger.getLogger(LogDomains.SECURITY_LOGGER);
    }
    /*
     * This method is invoked during server startup when the realm is
     * initially loaded.
     * The props argument contains the properties defined
     * for this realm in domain.xml.
     * The realm can do any initialization it needs in this method.
     * If the method returns without throwing an exception,
     * J2EE Application Server assumes the realm is ready
     * to service authentication requests.
     * If an exception is thrown, the realm eis disabled,
     * check the server.log for messages.
     */
   public void init(Properties props)
       throws BadRealmException, NoSuchRealmException{

       super.init(props);

       /*
        * Set the jaas context, otherwise server doesn't indentify the login module.
        * jaas-context is the property specified in domain.xml and
        * is the name corresponding to LoginModule
        * config/login.conf
        */
       String jaasCtx = props.getProperty(AppservRealm.JAAS_CONTEXT_PARAM);
       this.setProperty(AppservRealm.JAAS_CONTEXT_PARAM, jaasCtx);

       /*
        * Get any other interested properties from configuration file - domain.xml
        * 
        */
       String authTypeProp = props.getProperty(AUTH_TYPE_PARAM);
       this.authType = (authTypeProp != null) ? authTypeProp : AUTH_TYPE;

       String failedAttemptsProp = props.getProperty(FAILED_ATTEMPTS_PARAM);
       this.failedAttempts = Integer.valueOf((failedAttemptsProp != null) ? failedAttemptsProp : FAILED_ATTEMPTS);

       String lockedRoleProp = props.getProperty(LOCKED_ROLE_PARAM);
       this.lockedRole = (lockedRoleProp != null) ? lockedRoleProp : LOCKED_ROLE;

       String resetRoleProp = props.getProperty(RESET_ROLE_PARAM);
       this.resetRole = (resetRoleProp != null) ? resetRoleProp : RESET_ROLE;

       String jdbcResourceProp = props.getProperty(JDBC_RESOURCE_PARAM);
       this.jdbcResource = (jdbcResourceProp != null) ? jdbcResourceProp : JDBC_RESOURCE;

       String logLevelParam = props.getProperty(LOG_LEVEL_PARAM);
       logLevelParam = (logLevelParam != null) ? logLevelParam : LOG_LEVEL;
       if (logLevelParam.equalsIgnoreCase("INFO")) {
           this.logLevel = Level.INFO;
       } else if (logLevelParam.equalsIgnoreCase("SEVERE")) {
           this.logLevel = Level.SEVERE;
       } else if (logLevelParam.equalsIgnoreCase("WARNING")) {
           this.logLevel = Level.WARNING;
       } else if (logLevelParam.equalsIgnoreCase("OFF")) {
           this.logLevel = Level.OFF;
       } else if (logLevelParam.equalsIgnoreCase("FINEST")) {
           this.logLevel = Level.FINEST;
       }

       log("Initialized PHR Custom Realm");
   }

   private boolean createDS() {
       if (ds != null) return true;

       try {
           InitialContext ctx = new InitialContext();

           if (ctx == null) {
               log("JNDI problem, Cannot get Initial Context.");
               return false;
           }

           ds = (com.sun.appserv.jdbc.DataSource)ctx.lookup(jdbcResource);

           if (ds == null) {
               log("Unable to lookup datasource.");
               return false;
           }
       }
       catch (Exception e){
           log("Exception encountered in database connection initialization.");
           log(e.getMessage());
           return false;
       }
       return true;
   }

    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Not supported");
    }

   /**
     * Return a short description supported authentication by this realm.
     *
     * @return Description of the kind of authentication that is directly
     *     supported by this realm.
     */
   public String getAuthType(){
       return this.authType;
   }

   public String getLockedRole() {
       return this.lockedRole;
   }

   public String getResetRole(){
       return this.resetRole;
   }

   public String getJdbcResource(){
       return this.jdbcResource;
   }

   public Integer getFailedAttempts(){
       return this.failedAttempts;
   }

   public Level getLoggingLevel(){
       return this.logLevel;
   }


    /**
     * Returns names of all the users in this particular realm.
     *
     * @return enumeration of user names
     *
     */
    public Enumeration getUserNames() throws BadRealmException
    {
        String query = "SELECT username FROM user_users";
        ResultSet rs = null;
        Vector usernames = new Vector();
        PreparedStatement st = null;
        try {
            createDS();
            conn = ds.getNonTxConnection();
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
        }
        catch (Exception e){
            log("Error getting usernames from database");
            log(e.getMessage());
            rs = null;
        }
        finally {
            try {
                conn.close();
            }
            catch(Exception e) {
                log(e.getMessage());
            }
            conn = null;
        }
        if (rs != null) {
            try {
                rs.beforeFirst();
                while (rs.next()) {
                    usernames.add(rs.getString(1));
                }
            }
            catch (Exception e){
                log("Error getting usernames from resultset");
                log(e.getMessage());
            }
            finally {
                try {
                    st.close();
                    rs.close();
                }
                catch(Exception e) {
                    log(e.getMessage());
                }
            }
        }
        return usernames.elements();
    }


    /**
     * Returns the information recorded about a particular named user.
     *
     * This method always throws a BadRealmException since this method
     * is not supported in this context.
     *
     * @exception BadRealmException
     *
     */
    public User getUser(String name)
        throws NoSuchUserException, BadRealmException
    {
         throw new BadRealmException("Not supported");
    }

    /**
     * Returns names of all the groups in this particular realm.
     *
     * @return enumeration of group names (strings)
     *
     */
    public Enumeration getGroupNames()
        throws BadRealmException
    {
        //check role cache before querying

        String query = "SELECT UNIQUE role FROM user_roles";
        ResultSet rs = null;
        Vector roles = new Vector();
        PreparedStatement st = null;
        try {
            createDS();
            conn = ds.getNonTxConnection();
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
        }
        catch (Exception e){
            log("Error getting roles from database");
            log(e.getMessage());
            rs = null;
        }
        finally {
            try {
                conn.close();
            }
            catch(Exception e) {
                log(e.getMessage());
            }
            conn = null;
        }
        if (rs != null) {
            try {
                rs.beforeFirst();
                while (rs.next()) {
                    roles.add(rs.getString(1));
                }
            }
            catch (Exception e){
                log("Error getting roles from resultset");
                log(e.getMessage());
            }
            finally {
                try {
                    st.close();
                    rs.close();
                }
                catch(Exception e) {
                    log(e.getMessage());
                }
            }
        }
        return roles.elements();
    }

    /**
     * Returns enumeration of groups that a particular user belongs to.
     *
     *@exception NoSuchUserException
     */
    public Enumeration getGroupNames(String user)
        throws NoSuchUserException
    {
        //check user cache before querying?

        String query = "SELECT DISTINCT role FROM user_roles WHERE username = ?";
        ResultSet rs = null;
        Vector roles = new Vector();
        PreparedStatement st = null;
        try {
            createDS();
            conn = ds.getNonTxConnection();
            st = conn.prepareStatement(query);
            st.setString(1, user);
            rs = st.executeQuery();
        }
        catch (Exception e){
            log("Error getting roles from database");
            log(e.getMessage());
            rs = null;
        }
        finally {
            try {
                conn.close();
            }
            catch(Exception e) {
                log(e.getMessage());
            }
            conn = null;
        }
        if (rs != null) {
            try {
                rs.beforeFirst();
                while (rs.next()) {
                    roles.add(rs.getString(1));
                }
            }
            catch (Exception e){
                log("Error getting roles from resultset");
                log(e.getMessage());
            }
            finally {
                try {
                    st.close();
                    rs.close();
                }
                catch(Exception e) {
                    log(e.getMessage());
                }
            }
        } else {
            throw new NoSuchUserException("User not available.");
        }
        return roles.elements();
    }


    /**
     * Refreshes the realm data so that new users/groups are visible.
     *
     */
    public void refresh() throws BadRealmException
    {
        super.refresh();
    }

    /**
     * Checks the authentication of a user and returns the groups it belongs to.
     *
     * @return groups that this particular user belongs to
     */
    public String[] authenticateUser(String user, String password)
    {
        String query = "SELECT password, requires_reset, is_locked_out, active, failed_password_attempts FROM user_users WHERE username = ?";
        ResultSet rs = null;
        String passwordHash = new String();
        boolean requiresReset = false;
        boolean lockedOut = false;
        boolean active = false;
        int failedAttemptsVal = 0;
        PreparedStatement st = null;
        try {
            createDS();
            conn = ds.getNonTxConnection();
            st = conn.prepareStatement(query);
            st.setString(1, user);
            rs = st.executeQuery();
        }
        catch (Exception e){
            log("Error getting password from database");
            log(e.getMessage());
            rs = null;
        }
        finally {
            try {
                conn.close();
            }
            catch(Exception e) {
                log(e.getMessage());
            }
            conn = null;
        }
        if (rs != null) {
            try {
                if (rs.next()) {
                    passwordHash = rs.getString(1);
                    requiresReset = rs.getBoolean(2);
                    lockedOut = rs.getBoolean(3);
                    active = rs.getBoolean(4);
                    failedAttemptsVal = rs.getInt(5);
                }
            }
            catch (Exception e){
                log("Error getting password from resultset");
                log(e.getMessage());
            }
            finally {
                try {
                    st.close();
                    rs.close();
                }
                catch(Exception e) {
                    log(e.getMessage());
                }
            }
        }

        //inactive users have no roles, no login attempt monitoring
        if (!active) return null;

        //locked users have a locked role, which is filtered by the Login class as needed
        if (lockedOut){
            incrementFailedUpdate(user);
            String[] lock = {lockedRole};
            return lock;
        }

        if (!passwordHash.isEmpty()) {
            if (passwordHash.equals(DigestUtils.sha512Hex(password))) {
                //password is correct
                //find and return groups
                String[] retArr = null;
                try {
                    Enumeration userGroups = getGroupNames(user);
                    ArrayList retGroups = new ArrayList();

                    //populate with groups from db
                    while (userGroups.hasMoreElements()){
                        retGroups.add(userGroups.nextElement().toString());
                    }

                    //force password reset if needed by adding role
                    if (requiresReset) {
                        retGroups.add(resetRole);
                    }

                    //formulate returnable collection
                    Object[] arr = retGroups.toArray();
                    retArr = new String[arr.length];
                    for (int i=0; i<arr.length; i++){
                        retArr[i] = arr[i].toString();
                    }
                }
                catch (NoSuchUserException e){
                    log("Exception encountered looking up password");
                }
                catch (Exception e){
                    log("Group lookup error: " + e.getClass() + ":" + e.getMessage());
                }

                //reset bad login info, if needed, now that we're successful
                // this will only happen for valid logins and pw reset logins, not locks or inactive accts
                if (failedAttemptsVal > 0 ) doSuccessfulUpdate(user);

                return retArr;
            } else {
                log("Passwords do not match");
                try {
                InvalidPasswordAttempt(user);
                }
                catch (NoSuchUserException e) {
                    //not an issue here
                }
            }
        } else {
            log("Unable to find user password");
        }
        return null;

    }

    private void InvalidPasswordAttempt (String username) throws NoSuchUserException {
        String query = "SELECT failed_password_attempts, failed_password_window_start FROM user_users WHERE username = ?";
        ResultSet rs = null;
        PreparedStatement st = null;
        Timestamp windowStart = null;
        int failedAttemptsVal = 0;
        try {
            createDS();
            conn = ds.getNonTxConnection();
            st = conn.prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();
        }
        catch (Exception e){
            log("Error getting roles from database");
            log(e.getMessage());
            rs = null;
        }
        finally {
            try {
                conn.close();
            }
            catch(Exception e) {
                log(e.getMessage());
            }
            conn = null;
        }
        if (rs != null) {
            try {
                rs.beforeFirst();
                while (rs.next()) {
                    failedAttemptsVal = rs.getInt(1);
                    windowStart = rs.getTimestamp(2);
                }
            }
            catch (Exception e){
                log("Error getting invalid attempt values from resultset");
                log(e.getMessage());
            }
            finally {
                try {
                    st.close();
                    rs.close();
                }
                catch(Exception e) {
                    log(e.getMessage());
                }
            }
        } else {
            throw new NoSuchUserException("User not available.");
        }
        
        //take values and decide whether to lock account, increment failed attempts, or start new failure window
        if (windowStart != null) {
            //check if user has more than X previously existing failed logins
            if (Integer.valueOf(failedAttemptsVal).compareTo(failedAttempts) >= 0) {
                //lock out
                doFailedUpdate(username,null,failedAttemptsVal+1, true);
            } else {
                //dont lock account, just increment failed attempts
                doFailedUpdate(username, windowStart, failedAttemptsVal+1, false);
            }
        } else {
            //windowStart is null, set to now and set failed attempts to 1
            GregorianCalendar tempCal = new GregorianCalendar();
            windowStart = new java.sql.Timestamp(tempCal.getTimeInMillis());
            failedAttemptsVal = 1;
            doFailedUpdate(username,windowStart,failedAttemptsVal, false);
        }

    }

    //reset password attempts and password window
    private void doSuccessfulUpdate(String username) {
        String query = "UPDATE user_users SET failed_password_attempts = ? , failed_password_window_start = ? WHERE username = ?";
        PreparedStatement st = null;

        try {
            createDS();
            //TX for UPDATE
            conn = ds.getConnection();
            st = conn.prepareStatement(query);
            st.setInt(1, 0); //reset failed attempt count
            st.setTimestamp(2, null); //reset failed password timestamp
            st.setString(3, username);
            st.executeUpdate();
        }
        catch (Exception e){
            log("Error resetting failed password values");
            log(e.getMessage());
        }
        finally {
            try {
                st.close();
                conn.close();
            }
            catch(Exception e) {
                log(e.getMessage());
            }
            conn = null;
        }
    }

    //extracted to own method, not using doFailedUpdate, to avoid overhead of manipulating multiple fields
    private void incrementFailedUpdate(String username) {
        String query = "UPDATE user_users SET failed_password_attempts = failed_password_attempts + 1 WHERE username = ?";
        PreparedStatement st = null;

        try {
            createDS();
            //TX for UPDATE
            conn = ds.getConnection();
            st = conn.prepareStatement(query);
            st.setString(1, username);
            st.executeUpdate();
        }
        catch (Exception e){
            log("Error incrementing failed password value");
            log(e.getMessage());
        }
        finally {
            try {
                st.close();
                conn.close();
            }
            catch(Exception e) {
                log(e.getMessage());
            }
            conn = null;
        }
    }

    private void doFailedUpdate(String username, java.sql.Timestamp windowStart, int failedAttempts, boolean setLock){
        String query = "UPDATE user_users SET failed_password_attempts = ? , failed_password_window_start = ? , is_locked_out = ?, lockout_begin = ? WHERE username = ?";
        PreparedStatement st = null;
        java.sql.Timestamp lockoutBegin = null;
        
        if (setLock) {
            GregorianCalendar tempCal = new GregorianCalendar(java.util.TimeZone.getTimeZone("GMT"));
            lockoutBegin = new java.sql.Timestamp(tempCal.getTimeInMillis());
        }

        try {
            createDS();
            //TX for UPDATE
            conn = ds.getConnection();
            st = conn.prepareStatement(query);
            st.setInt(1, failedAttempts);
            st.setTimestamp(2, windowStart);
            st.setBoolean(3, setLock);
            st.setTimestamp(4, lockoutBegin);
            st.setString(5, username);
            st.executeUpdate();
        }
        catch (Exception e){
            log("Error updating failed password values");
            log(e.getMessage());
        }
        finally {
            try {
                st.close();
                conn.close();
            }
            catch(Exception e) {
                log(e.getMessage());
            }
            conn = null;
        }
    }

    public static Logger getLogger() {
        return _logger;
    }

    /*
     * Helper method
     *
     * Simple message print method used throughout the program
     */
    public void log(String message){
        if (logLevel == null) logLevel = Level.INFO;
           _logger.log(logLevel, "PHRRealm:" + message );
    }
}
