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
package com.krminc.phr.domain;

import com.krminc.phr.core.UserConfig;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User entity class.
 * 
 * @author Daniel Shaw (dshaw.com)
 */
@Entity
@Table(name = "user_users")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByTitle", query = "SELECT u FROM User u WHERE u.title = :title"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByMiddleName", query = "SELECT u FROM User u WHERE u.middleName = :middleName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findBySuffix", query = "SELECT u FROM User u WHERE u.suffix = :suffix"),
    @NamedQuery(name = "User.findByPreferredName", query = "SELECT u FROM User u WHERE u.preferredName = :preferredName"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active"),
    @NamedQuery(name = "User.findByDateCreated", query = "SELECT u FROM User u WHERE u.dateCreated = :dateCreated"),
    @NamedQuery(name = "User.findByLastLogin", query = "SELECT u FROM User u WHERE u.lastLogin = :lastLogin"),
    /* --- Custom Queries --- */
    @NamedQuery(name = "User.findUserIdByUsername", query = "SELECT u.userId FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findUsernameByUserId", query = "SELECT u.username FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findFullnameByUserId", query = "SELECT u.firstName, u.lastName FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.countAll", query = "SELECT COUNT(u) FROM User u"),
    @NamedQuery(name = "User.updateLoggedInTime", query = "UPDATE User u SET u.lastLogin = CURRENT_TIMESTAMP WHERE u.userId = :userId"),
    @NamedQuery(name = "User.updateLoggedInTotal", query = "UPDATE User u SET u.totalLogin = u.totalLogin + 1 WHERE u.userId = :userId")
})
public class User implements Serializable {
    
    private static final long serialVersionUID = 20091123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 64)
    private String username;
    @Basic(optional = false)
    @Column(name = "password", length = 256)
    private String password;
    @Column(name = "title", length = 10)
    private String title;
    @Basic(optional = false)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "middle_name", length = 50)
    private String middleName;
    @Basic(optional = false)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "suffix", length = 10)
    private String suffix;
    @Column(name = "preferred_name", length = 50)
    private String preferredName;

    @Column(name = "telnum_home", length = 25)
    private String telnumHome;
    @Column(name = "telnum_work", length = 25)
    private String telnumWork;
    @Column(name = "telnum_mobile", length = 25)
    private String telnumMobile;
    @Column(name = "faxnum", length = 25)
    private String faxnum;

    @Column(name = "email", length = 200)
    private String email;

    @Column(name = "question1_id", nullable = false)
    private int question1Id;
    @Column(name = "question1_answer", nullable = false, length = 50)
    private String question1Answer;
    @Column(name = "question2_id", nullable = false)
    private int question2Id;
    @Column(name = "question2_answer", nullable = false, length = 50)
    private String question2Answer;

    @Column(name="active")
    protected Boolean active;

    @Basic(optional = false)
    @Column(name="requires_reset")
    protected Boolean requiresReset;
    
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_login", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(name = "total_login", nullable = false)
    private Integer totalLogin;

    //03112010 new lockout & failed attempt mappings

    @Basic(optional = false)
    @Column(name="is_locked_out")
    protected Boolean isLockedOut;

    @Column(name = "lockout_begin", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockoutBegin;

    @Basic(optional = false)
    @Column(name = "failed_password_attempts", nullable = false)
    private int failedPasswordAttempts;

    @Column(name = "failed_password_window_start", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date failedPasswordWindowStart;
    
    @Basic(optional = false)
    @Column(name = "failed_answer_attempts", nullable = false)
    private int failedAnswerAttempts;

    @Column(name = "failed_answer_window_start", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date failedAnswerWindowStart;

    @Basic(optional = true, fetch=FetchType.LAZY)
    @Lob
    @Column(name = "user_image", nullable = true)
    private byte[] userImage;

    //FKs

    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
            joinColumns=@JoinColumn(name="username", referencedColumnName="username"),
            inverseJoinColumns=@JoinColumn(name="username", referencedColumnName="username"))
    private List<UserRole> roles;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy = "user")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Address> addresses;

    //@OneToMany(cascade=CascadeType.PERSIST, mappedBy = "user")
    @ManyToMany(cascade = CascadeType.ALL) //(cascade=CascadeType.PERSIST, mappedBy = "userList")
    @JoinTable(name="user_users_rec_healthrecord", 
        joinColumns = {
            @JoinColumn(name = "user_id") //, referencedColumnName = "user_user_id")
        }, inverseJoinColumns = {
            @JoinColumn(name="rec_id")
        }
    )
    private List<HealthRecord> healthRecords;

    transient final Logger logger = LoggerFactory.getLogger(User.class);

    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
    }

    public User(Long userId, String username, String firstName, String middleName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.requiresReset = true;
        this.active = true;
        this.isLockedOut = false;
    }

    // TODO: Setup Builder pattern for this.
    public User(Long userId, String username, String password, String firstName, String middleName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.setPassword(password); // to ensure password is hashed.
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.requiresReset = true;
        this.active = true;
        this.isLockedOut = false;
    }

    /**
     * Constructor for user enrollment form.
     *
     * @param username
     * @param firstName
     * @param middleName
     * @param lastName
     * @param dateCreated
     */
    public User(String username, String firstName, String middleName, String lastName, Date dateCreated) {
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateCreated = dateCreated;
        this.requiresReset = true;
        this.active = true;
        this.isLockedOut = false;
    }

    /**
     * Constructor for user enrollment form.
     *
     * @param username
     * @param password
     * @param firstName
     * @param middleName
     * @param lastName
     * @param dateCreated
     */
    public User(String username, String password, String firstName, String middleName, String lastName, Date dateCreated) {
        this.username = username;
        this.setPassword(password); // to ensure password is hashed.
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateCreated = dateCreated;
        this.requiresReset = true;
        this.active = true;
        this.isLockedOut = false;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha512Hex(password);
    }

    public Boolean testPassword(String testPassword) {
        return (this.password.equals(DigestUtils.sha512Hex(testPassword)));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getFaxnum() {
        return faxnum;
    }

    public void setFaxnum(String faxnum) {
        this.faxnum = faxnum;
    }

    public String getTelnumHome() {
        return telnumHome;
    }

    public void setTelnumHome(String telnumHome) {
        this.telnumHome = telnumHome;
    }

    public String getTelnumMobile() {
        return telnumMobile;
    }

    public void setTelnumMobile(String telnumMobile) {
        this.telnumMobile = telnumMobile;
    }

    public String getTelnumWork() {
        return telnumWork;
    }

    public void setTelnumWork(String telnumWork) {
        this.telnumWork = telnumWork;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuestion1Id() {
        return question1Id;
    }

    public void setQuestion1Id(int question1Id) {
        this.question1Id = question1Id;
    }

    public String getQuestion1Answer() {
        return question1Answer;
    }

    public void setQuestion1Answer(String question1Answer) {
        this.question1Answer = question1Answer;
    }

    public int getQuestion2Id() {
        return question2Id;
    }

    public void setQuestion2Id(int question2Id) {
        this.question2Id = question2Id;
    }

    public String getQuestion2Answer() {
        return question2Answer;
    }

    public void setQuestion2Answer(String question2Answer) {
        this.question2Answer = question2Answer;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getRequiresReset() {
        return requiresReset;
    }

    public void setRequiresReset(Boolean requiresReset) {
        this.requiresReset = requiresReset;
    }
    
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public Integer getTotalLogin() {
        return totalLogin;
    }

    public void setTotalLogin(Integer total) {
        this.totalLogin = total;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    //03112010 new lockout & failed attempt methods

    public int getFailedAnswerAttempts() {
        return failedAnswerAttempts;
    }

    public void setFailedAnswerAttempts(int failedAnswerAttempts) {
        this.failedAnswerAttempts = failedAnswerAttempts;
    }

    public Date getFailedAnswerWindowStart() {
        return failedAnswerWindowStart;
    }

    public void setFailedAnswerWindowStart(Date failedAnswerWindowStart) {
        this.failedAnswerWindowStart = failedAnswerWindowStart;
    }

    public int getFailedPasswordAttempts() {
        return failedPasswordAttempts;
    }

    public void setFailedPasswordAttempts(int failedPasswordAttempts) {
        this.failedPasswordAttempts = failedPasswordAttempts;
    }

    public Date getFailedPasswordWindowStart() {
        return failedPasswordWindowStart;
    }

    public void setFailedPasswordWindowStart(Date failedPasswordWindowStart) {
        this.failedPasswordWindowStart = failedPasswordWindowStart;
    }

    public Boolean getIsLockedOut() {
        return isLockedOut;
    }

    public void setIsLockedOut(Boolean isLockedOut) {
        this.isLockedOut = isLockedOut;
    }

    public Date getLastLockout() {
        return lockoutBegin;
    }

    public void setLastLockout(Date lastLockout) {
        this.lockoutBegin = lastLockout;
    }

    // TODO: Apply to API.
    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    /**
     * @return whether user is a patient.
     */
    public boolean isPatient() {
        for (UserRole role : roles) {
            if (role.getRole().equals(UserConfig.ROLE_PATIENT)) {
                return true;
            }
        }
        return false;
    }

    /**
     * JSP compatible getter for isPatient().
     * @return whether user is patient.
     */
    public boolean getIsPatient() {
        return isPatient();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        for (Address address : addresses) {
            address.setUser(this);
        }
        this.addresses = addresses;
    }

    public Address getPrimaryAddress() {
        for(Address address : addresses) {
            if (address.getPrimaryAddress()) {
                return address;
            }
        }
        // if no address is found, return an empty object.
        return new Address();
    }

    public int getAddressCount() {
        return getAddresses().size();
    }

    public List<HealthRecord> getHealthRecords() {
        return healthRecords;
    }

    public void setHealthRecords(List<HealthRecord> healthRecords) {
        this.healthRecords = healthRecords;
    }

    public HealthRecord getPrimaryHealthRecord() {
        for(HealthRecord healthRecord : healthRecords) {
            if (healthRecord.isPrimary()) {
                return healthRecord;
            }
        }
        // blank health record
        return new HealthRecord();
    }

    public int getHealthRecordCount() {
        return getHealthRecords().size();
    }

    public boolean authorizedToAccessHealthRecord(HealthRecord healthRecord) {
        return authorizedToAccessHealthRecord(healthRecord.getHealthRecordId());
    }

    public boolean authorizedToAccessHealthRecord(Long healthRecordId) {
        for (HealthRecord healthRecord : healthRecords) {
            logger.debug(
                    "Scanning to see if userid {} has access to recid {}",
                    this.userId, healthRecord.getHealthRecordId()
                    );
            if (healthRecordId.equals(healthRecord.getHealthRecordId())) {
                logger.debug(
                        "Attempting to allow access by  userid {} to recid {}",
                        this.userId, healthRecord.getHealthRecordId()
                        );
                return true;
            }
        }

        logger.error(
                "Disallowing access by userid {} to recid {}",
                this.userId, healthRecordId
                );
        return false;
    }


    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(java.io.InputStream userImage) {
        ArrayList<Byte> list = new ArrayList<Byte>();
        int s;

        //loop through each byte of input image
        try {
            while ((s = userImage.read()) != -1) {
                    list.add((byte) s);
            }
        }
        catch (IOException e) {
            list.clear();
        }

        //copy from arraylist to ORM-pleasing byte array
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
                b[i] = list.get(i);
        }

        if (list.size() > 0) {
            this.userImage = b;
        } else {
            this.userImage = null;
        }

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.User[userId=" + userId + " username=" + username + "]";
    }

}