--
-- Copyright (C) 2012 KRM Associates, Inc. healtheme@krminc.com
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--         http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

USE phr;

-- setup user accounts
INSERT INTO `phr`.`user_users`
    (username, password, requires_reset,
        title, first_name, middle_name, last_name, suffix, preferred_name, email,
        telnum_home, telnum_mobile, telnum_work, faxnum ,date_created)
VALUES
    ('johndoe', 'cbda1c52153b137c8543bec07a3eccd5a478c73930bbe46167e02abd1faf570bd6651890913a49574c64c70f6924e4075309da7a76b3ae7a94673c47a1086ba5', 0,
        NULL, 'John', 'G', 'Doe', NULL, 'John','johndoe@example.com',
        '555-555-5555', '555-555-5555', '555-555-5555', '555-555-5555', CURRENT_TIMESTAMP);

INSERT INTO `phr`.`user_addresses`
    (user_id,primary_address,address1,address2,city,state,province,zip,country,created_by_user_id,last_updated)
SELECT 
    MAX(user_id), 1, '123 Fake St.', 'Apt 2B', 'Shepherdstown', 'WV', NULL, '25443', 'us', 1, NULL FROM user_users;

INSERT INTO `phr`.`user_roles`
    (username, role, date_created)
VALUES
    ('johndoe', 'ROLE_PATIENT', CURRENT_TIMESTAMP);

-- create health records for users.

INSERT INTO `phr`.`rec_health_records`
    (primary_rec, gender, date_of_birth, marital_status, blood_type, organ_donor, date_created)
VALUES
    (1, 'M', '1955-10-10', 'M', 'A', 1, CURRENT_TIMESTAMP);

-- Insert most recently inserted rec_id and user_id (both johndoe's) to coordinate
INSERT INTO phr.user_users_rec_healthrecord (rec_id, user_id) 
SELECT MAX(r.rec_id), MAX(u.user_id) FROM rec_health_records r, user_users u;

-- insert test patient self-entered data
INSERT INTO `phr`.`data_medicalevents`
    (`rec_id`,`event`,`comments`,`observed_date`,`source_id`,`data_source_id`,`care_document_id`,`mask`)
VALUES
    (1,'Tonsils','Tonsils were removed','2002-10-02',1,1,NULL,NULL),
    (1,'Appendicitis','Appendix removed','2001-01-03',1,1,NULL,NULL),
    (1,'Cancer','Malignant tumor','2005-08-12',1,1,NULL,NULL),
    (1,'Scar removed','Left upper bicep','2001-01-01',1,1,NULL,NULL);

INSERT INTO `phr`.`data_allergies`
    (rec_id, data_source_id, care_document_id, source_id, allergy_text, observed_date, severity, reaction, comments, mask)
VALUES
    (1, 1, NULL, 1, 'Peanuts', '2008-06-04', 'Medium', 'Fever', NULL, NULL),
    (1, 1, NULL, 1, 'Hay', '2009-07-04', 'Severe', 'Welts', 'Could be deadly', NULL);

INSERT INTO phr.data_medications
    (rec_id, medication_text, dose, frequency, rx_id, status, start_date, end_date, reason, category, comments, source_id, data_source_id, care_document_id, mask)
VALUES
    (1, 'Lipitor', '1 pill', 'Daily', '125735', 'Active', '2008-09-09', '2010-01-09', 'Cholesterol problem', 1, 'LDL needs to be reduced', 1, 1, NULL, NULL),
    (1, 'Vicodin', '1 pill', 'Daily', '11122', 'Inactive', '2004-10-09', NULL, 'Pain prescription', 1, 'Heavy sedative', 1, 1, NULL, NULL);

INSERT INTO `phr`.`data_immunizations`
    (rec_id, date_received, immunization_type, method, reaction, comments, source_id, data_source_id, care_document_id, mask)
VALUES
    (1, '2008-07-01', 'Hepatitis A', 1, 'Weakness', 'Successfully administered Hepatitis A immunization', 1, 1, NULL, NULL),
    (1, '2002-07-28', 'Tetanus', 2, 'Nausea/Vomiting', 'Made patient ill', 1, 1, NULL, NULL),
    (1, '2008-07-05', 'Hepatitis C', 3, 'Chills, Unusual Redness of Skin', 'Successfully administered Hepatitis C immunization', 1, 1, NULL, NULL),
    (1, '2008-05-01', 'Flu', 0, 'Weakness', NULL, 1, 1, NULL, NULL);

INSERT INTO `phr`.`data_visits`
    (rec_id, title,  visit_date, visit_time, purpose, location, provider, comments, source_id, data_source_id, care_document_id, mask )
VALUES
    (1, 'Checkup with Dr. Jenkins', '2009-11-02', '17:30', 'Annual checkup', 'West Side Office', 'Dr. Jenkins', 'Ask about tooth decay', 1, 1, NULL, NULL);

-- insert test patient self-entered vital data
INSERT INTO `phr`.`data_vitals_bloodpressure`
    (`rec_id`,`systolic`,`diastolic`,`observed_date`,`source_id`,`data_source_id`,`care_document_id`,`mask`)
VALUES
    (1,128,98,'2001-01-02',1,1,NULL,NULL),
    (1,120,92,'2001-01-03',1,1,NULL,NULL),
    (1,122,91,'2001-01-04',1,1,NULL,NULL),
    (1,121,90,'2001-01-01',1,1,NULL,NULL);

INSERT INTO `phr`.`data_vitals_bloodsugar`
    (`rec_id`,`bloodsugar_id`,`level`,`method`,`unit`,`observed_date`,`source_id`,`data_source_id`,`care_document_id`,`mask`)
VALUES
    (1,1,'154','Ear','mg/dL','2006-07-18',1,1,NULL,NULL);

INSERT INTO `phr`.`data_vitals_heartrate`
    (`rec_id`,`heart_rate_id`,`rate`,`observed_date`,`source_id`,`data_source_id`,`care_document_id`,`mask`)
VALUES
    (1,1,'78','2008-06-25',1,1,NULL,NULL);

INSERT INTO `phr`.`data_vitals_height`
    (`rec_id`,`height_id`,`feet`,`inches`,`observed_date`,`source_id`,`data_source_id`,`care_document_id`,`mask`)
VALUES
    (1,1,5,10,'2008-09-15',1,1,NULL,NULL);

INSERT INTO `phr`.`data_vitals_pain`
    (`rec_id`,`pain_id`,`value`,`observed_date`,`source_id`,`data_source_id`,`care_document_id`,`mask`)
VALUES
    (1,1,7,'2001-11-08',1,1,NULL,NULL);

INSERT INTO `phr`.`data_vitals_peakflow`
    (`rec_id`,`peakflow_id`,`peak_flow`,`observed_date`,`source_id`,`data_source_id`,`care_document_id`,`mask`)
VALUES
    (1,1,'133','2006-05-30',1,1,NULL,NULL);

INSERT INTO `phr`.`data_vitals_temperature`
    (`rec_id`,`temperature_id`,`temperature`,`method`,`unit`,`observed_date`,`source_id`,`data_source_id`,`care_document_id`,`mask`)
VALUES
    (1,1,'101','Ear','F','2009-10-15',1,1,NULL,NULL);

INSERT INTO `phr`.`data_vitals_weight`
    (`rec_id`,`weight_id`,`weight`,`unit`,`observed_date`,`source_id`,`data_source_id`,`care_document_id`,`mask`)
VALUES
    (1,1,215,'lbs','2009-04-11',1,1,NULL,NULL);

