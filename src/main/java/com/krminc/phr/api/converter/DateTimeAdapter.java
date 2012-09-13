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
/**
 *
 * @author cmccall
 */
public class DateTimeAdapter extends XmlAdapter<String, Date> {

    DateFormat df;

    public Date unmarshal(String date) throws Exception {
        df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        if (df.parse(date) != null) {
            return df.parse(date);
        }

        df = new SimpleDateFormat("MM/dd/yyyy hh a");
        if (df.parse(date) != null) {
            return df.parse(date);
        }

        df = new SimpleDateFormat("MM/dd/yyyy hh");
        if (df.parse(date) != null) {
            return df.parse(date);
        }

        return null;
    }

    public String marshal(Date date) throws Exception {
        df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        if (df.format(date) != null) {
            return df.format(date);
        }

        df = new SimpleDateFormat("MM/dd/yyyy hh a");
        if (df.format(date) != null) {
            return df.format(date);
        }

        df = new SimpleDateFormat("MM/dd/yyyy hh");
        if (df.format(date) != null) {
            return df.format(date);
        }

        return null;
    }
}
