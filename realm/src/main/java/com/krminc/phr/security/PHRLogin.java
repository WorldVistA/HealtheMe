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

import com.sun.appserv.security.AppservPasswordLoginModule;
import javax.security.auth.login.LoginException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.AccountLockedException;

import java.util.logging.Logger;
import java.util.logging.Level;
import com.sun.logging.LogDomains;

/**
 *
 * @author cmccall
 */
public class PHRLogin extends AppservPasswordLoginModule {

    private static Logger _logger=null;
    private static Level logLevel = Level.INFO;
    static{
        _logger = Logger.getLogger(LogDomains.SECURITY_LOGGER);
    }
    /*
     * Custom realm implementation:only the following
     * method need to be implemented.
     *
     */

    protected void authenticateUser()
        throws LoginException, FailedLoginException{

        //log("CustomRealm Auth Info:_username:"+_username+"; _password:"+_password+";_currentrealm:"+_currentRealm);

        // Get the current realm and check whether it is instance of your realm
        if (!(_currentRealm instanceof PHRRealm)) {
            throw new LoginException("PHRRealm : Bad Realm");
        }
        PHRRealm realm = (PHRRealm)_currentRealm;

        String[] grpList = realm.authenticateUser(_username, _password);

        if (grpList == null) {  // JAAS behavior
            throw new FailedLoginException("PHRRealm : Login Failed/Inactive with user " + _username);
        } else if (grpList.length > 0 && grpList[0].equalsIgnoreCase(realm.getLockedRole())) {
            throw new AccountLockedException("PHRRealm : Login Locked for user " + _username);
        }

        log("login succeeded for  " + _username);

        // Add the code related to authenticating to your user database.
        String[] groupListToForward = (String[])grpList.clone();

        /*
         * Call the commitAuthentication to populate
         * grpList with the set of groups to which
         * _username belongs in this realm.
         */

        /* commitUserAuthentication(_username, _password,
                             _currentRealm, groupListToForward);
        */
        commitUserAuthentication(groupListToForward);
    }


     /*
     * Helper methods.
     *
     * Simple message print method used throught the program
     */
    public void log(String mesg){
        _logger.log(logLevel, "PHRLogin:"+mesg );
    }
}
