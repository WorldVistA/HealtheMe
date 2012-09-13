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
package com.krminc.phr.api.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 *
 * @author cmccall
 */
public class TimeAdapter extends XmlAdapter<String, Date> {

//  final Logger logger = LoggerFactory.getLogger(TimeAdapter.class);
    DateFormat df;

    public Date unmarshal(String date) throws Exception {
//    logger.debug("TIMEADAPTER String: ", date);
        df = new SimpleDateFormat("hh:mm a");

        if (df.parse(date) != null) {
            return df.parse(date);
        }

        df = new SimpleDateFormat("hh a");
        if (df.parse(date) != null) {
            return df.parse(date);
        }

        df = new SimpleDateFormat("hh");
        if (df.parse(date) != null) {
            return df.parse(date);
        }

        return null;
    }

    public String marshal(Date date) throws Exception {
//      logger.debug("TIMEADAPTER Date:", date.toString());
        df = new SimpleDateFormat("hh:mm a");

        if (df.format(date) != null) {
            return df.format(date);
        }

        df = new SimpleDateFormat("hh a");
        if (df.format(date) != null) {
            return df.format(date);
        }

        df = new SimpleDateFormat("hh");
        if (df.format(date) != null) {
            return df.format(date);
        }

        return null;
    }
}
