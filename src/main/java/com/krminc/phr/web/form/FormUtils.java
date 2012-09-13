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
package com.krminc.phr.web.form;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Set of utilities for form backing objects.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class FormUtils {

    // Suppress default constructor for noninstantiability
    private FormUtils() {
        throw new AssertionError();
    }

    // TODO: Convert to Google Collections immutable list.
    public static final List<Integer> dobYears() {
        List<Integer> years = new ArrayList<Integer>();
        for ( int i = new DateTime().getYear(); i > 1900; i-- ) {
            years.add(i);
        }
        return years;
    }


}
