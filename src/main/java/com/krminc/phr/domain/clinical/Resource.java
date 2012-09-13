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
package com.krminc.phr.domain.clinical;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Resource entity class.
 *
 * @author chaz
 * @author Daniel Shaw (dshaw.com)
 */
@Entity
@Table(name="rec_resources")
@NamedQueries({
    @NamedQuery(name = "Resource.findAll", query = "SELECT r FROM Resource r"),
    @NamedQuery(name = "Resource.findByActive", query = "SELECT r FROM Resource r WHERE r.active = :active ORDER BY r.id")
})

public class Resource implements Serializable {
    
    private static final long serialVersionUID = 20091209L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="resource_id")
    private Long id;

    @Column(name="active")
    protected Boolean active;

    @Column(name="type")
    protected String type;

    @Column(name="display_name")
    protected String displayName;

    @Column(name="resource_location_path")
    protected String resourceLocationPath;

    @Column(name="resource_namespace")
    protected String resourceNamespace;

    @Column(name="resource_local_part")
    protected String resourceLocalPart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="added_date_time")
    protected Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_updated_date_time")
    protected Date lastUpdate;

    // TODO: this should be @OneToOne.
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="rec_resource_credentials_map",
        joinColumns={@JoinColumn(name="resource_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "credential_id")} )
    protected List <ResourceCredential> resourceCredentials;


    public Resource() {
    }

    public Resource(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * More semantic version of active property getter.
     *
     * @return active state
     */
    public Boolean isActive() {
        return active;
    }

    /**
     * Required getter form for use with web frameworks.
     *
     * @return active state
     */
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getResourceLocalPart() {
        return resourceLocalPart;
    }

    public void setResourceLocalPart(String resourceLocalPart) {
        this.resourceLocalPart = resourceLocalPart;
    }

    public String getResourceLocationPath() {
        return resourceLocationPath;
    }

    public void setResourceLocationPath(String resourceLocationPath) {
        this.resourceLocationPath = resourceLocationPath;
    }

    public String getResourceNamespace() {
        return resourceNamespace;
    }

    public void setResourceNamespace(String resourceNamespace) {
        this.resourceNamespace = resourceNamespace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ResourceCredential> getResourceCredentials() {
        return resourceCredentials;
    }

    public void setResourceCredentials(List<ResourceCredential> resourceCredentials) {
        this.resourceCredentials = resourceCredentials;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
