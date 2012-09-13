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

DROP DATABASE IF EXISTS phr;

CREATE DATABASE phr DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE phr;

-- user tables:

CREATE TABLE phr.user_users (
  user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(64) NOT NULL UNIQUE,
  password varchar(256),
  requires_reset tinyint(1) DEFAULT 1,
  title varchar(10),
  first_name varchar(100) NOT NULL,
  middle_name varchar(50),
  last_name varchar(50) NOT NULL,
  suffix varchar(10),
  preferred_name varchar(50),
  email varchar(200),
  telnum_home varchar(25),
  telnum_mobile varchar(25),
  telnum_work varchar(25),
  faxnum varchar(25),
  question1_id int unsigned,
  question1_answer varchar(50),
  question2_id int unsigned,
  question2_answer varchar(50),
  active tinyint(1) DEFAULT 1,
  date_created datetime NOT NULL,
  created_by BIGINT,
  last_login timestamp NULL DEFAULT NULL,
  total_login BIGINT DEFAULT 0,
  is_locked_out tinyint(1) DEFAULT 0,
  lockout_begin timestamp NULL DEFAULT NULL,
  failed_password_attempts int unsigned DEFAULT 0,
  failed_password_window_start timestamp NULL DEFAULT NULL,
  failed_answer_attempts int unsigned DEFAULT 0,
  failed_answer_window_start timestamp NULL DEFAULT NULL,
  user_image BLOB NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.user_roles (
  id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(64) REFERENCES user_users(username) ON DELETE CASCADE,
  role VARCHAR(64) NOT NULL,
  date_created datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.user_addresses (
  address_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  primary_address tinyint(1) DEFAULT 0,
  address1 varchar(200),
  address2 varchar(200),
  address3 varchar(200),
  city varchar(100),
  state varchar(100),
  province varchar(100),
  zip varchar(25),
  country varchar(25),
  date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by_user_id BIGINT,
  last_updated datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- self entered data tables:

CREATE  TABLE phr.data_allergies (
  allergy_id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  rec_id bigint unsigned NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  allergy_text VARCHAR(75) NULL DEFAULT NULL ,
  observed_date DATE NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  severity VARCHAR(75) NULL DEFAULT NULL ,
  reaction VARCHAR(255) NULL DEFAULT NULL ,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE  TABLE phr.system_data_sources (
  data_source_id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  data_source_name VARCHAR(100) NOT NULL ,
  description VARCHAR(500) NULL DEFAULT NULL ,
  modifiable TINYINT(1) NULL DEFAULT FALSE ,
  date_added DATE NULL DEFAULT NULL ,
  notes VARCHAR(4000) NULL DEFAULT NULL ,
  data_type ENUM('ccr','ccd') NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_immunizations (
  immunization_id BIGINT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date_received date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  immunization_type varchar(255) NOT NULL,
  method int unsigned DEFAULT NULL,
  reaction varchar(255) DEFAULT NULL,
  comments varchar(512) DEFAULT NULL,
  rec_id bigint unsigned NOT NULL,
  source_id BIGINT unsigned NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_medicalevents (
  medical_event_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  event varchar(512) NOT NULL,
  comments varchar(512) DEFAULT NULL,
  status varchar(512) DEFAULT NULL,
  how_ended varchar(512) DEFAULT NULL,
  observed_date date DEFAULT NULL,
  resolved_date date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_medications (
  medication_id BIGINT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  medication_text varchar(200) NOT NULL,
  dose varchar(75) DEFAULT NULL,
  frequency varchar(75) DEFAULT NULL,
  rx_id varchar(75) DEFAULT NULL,
  status enum('Active','Inactive') DEFAULT NULL,
  start_date DATE,
  end_date DATE,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  reason varchar(255) DEFAULT NULL,
  category int unsigned DEFAULT NULL,
  comments varchar(512) DEFAULT NULL,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.data_visits (
  visit_id BIGINT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title varchar(255) NOT NULL,
  visit_date DATE,
  visit_time TIME,
  purpose varchar(255) DEFAULT NULL,
  location varchar(255) DEFAULT NULL,
  provider varchar(255) DEFAULT NULL,
  comments varchar(512) DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.data_exercises (
  exercise_id BIGINT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title varchar(255) NOT NULL,
  exercise_date DATE NOT NULL,
  exercise_time TIME NOT NULL,
  exercise_duration_hours int unsigned NOT NULL,
  exercise_duration_minutes int unsigned NOT NULL,
  comments varchar(512) DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE  TABLE phr.data_exercises_list (
  exercise_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  exercise_description VARCHAR(45) NULL ,
  exercise_display TINYINT NULL ,
  acsm_category VARCHAR(45) NULL ,
  acsm_description VARCHAR(255) NULL ,
  acsm_compcode CHAR(5) NULL ,
  acsm_mets FLOAT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_vitals_bloodpressure (
  bloodpressure_id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  systolic bigint NOT NULL,
  diastolic bigint NOT NULL,
  observed_date date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_vitals_bloodsugar (
  bloodsugar_id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  level varchar(75) NOT NULL,
  method varchar(255) DEFAULT NULL,
  unit enum('mg/dL','mmol/L','Other') DEFAULT NULL,
  observed_date date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_vitals_heartrate (
  heart_rate_id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  rate int unsigned NOT NULL,
  observed_date date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_vitals_height (
  height_id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  feet INTEGER NOT NULL,
  inches INTEGER NOT NULL,
  observed_date date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_vitals_pain (
  pain_id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  value INTEGER NOT NULL,
  observed_date date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_vitals_peakflow (
  peakflow_id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  peak_flow integer unsigned NOT NULL,
  observed_date date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_vitals_temperature (
  temperature_id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  temperature varchar(75) NOT NULL,
  method varchar(255) DEFAULT NULL,
  unit enum('F','C','Other') DEFAULT NULL,
  observed_date date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  phr.data_vitals_weight (
  weight_id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  weight int unsigned NOT NULL,
  unit enum('lbs','kgs') DEFAULT NULL,
  observed_date date DEFAULT NULL,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rec_id bigint unsigned NOT NULL,
  source_id bigint unsigned NOT NULL,
  data_source_id bigint unsigned NOT NULL,
  care_document_id BIGINT unsigned DEFAULT NULL,
  mask varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- clinical data tables:

CREATE TABLE phr.rec_health_records (
  rec_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  primary_rec tinyint(1) DEFAULT 0,
  gender enum('M','F') NOT NULL,
  date_of_birth date,
  marital_status enum('S','M','D'),
  blood_type enum('A','B','AB','O'),
  organ_donor tinyint(1),
  date_created datetime NOT NULL,
  notes varchar(255),
  ccr_id BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Care Notebook Contributions

CREATE TABLE phr.carenotebook_emergencyinformation (
  emergencyinformation_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ambulance_number VARCHAR(25) NULL DEFAULT NULL,
  physician_name VARCHAR(50) NULL DEFAULT NULL,
  physician_number VARCHAR(25) NULL DEFAULT NULL,
  fire_number VARCHAR(25) NULL DEFAULT NULL,
  police_number VARCHAR(25) NULL DEFAULT NULL,
  poison_number VARCHAR(25) NULL DEFAULT NULL,
  father_name VARCHAR(50) NULL DEFAULT NULL,
  father_number VARCHAR(25) NULL DEFAULT NULL,
  mother_name VARCHAR(50) NULL DEFAULT NULL,
  mother_number VARCHAR(25) NULL DEFAULT NULL,
  emergency_contact_name VARCHAR(50) NULL DEFAULT NULL,
  emergency_contact_relationship VARCHAR(100) NULL DEFAULT NULL,
  emergency_contact_number VARCHAR(25) NULL DEFAULT NULL,
  hospital_name VARCHAR(50) NULL DEFAULT NULL,
  hospital_number VARCHAR(25) NULL DEFAULT NULL,
  hospital_address VARCHAR(200) NULL DEFAULT NULL,
  hospital_address2 VARCHAR(200) NULL DEFAULT NULL,
  hospital_city VARCHAR(100) NULL DEFAULT NULL,
  hospital_state VARCHAR(100) NULL DEFAULT NULL,
  specialty_name VARCHAR(50) NULL DEFAULT NULL,
  specialty_number VARCHAR(25) NULL DEFAULT NULL,
  specialty_type VARCHAR(100) NULL DEFAULT NULL,
  emergency_description VARCHAR(4000) NULL DEFAULT NULL,
  treatment_description VARCHAR(4000) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE phr.carenotebook_insuranceinformation (
  insuranceinformation_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  insurance_name VARCHAR(50) NULL DEFAULT NULL,
  insurance_identification VARCHAR(50) NULL DEFAULT NULL,
  group_number VARCHAR(50) NULL DEFAULT NULL,
  claims_address VARCHAR(200) NULL DEFAULT NULL,
  claims_address2 VARCHAR(200) NULL DEFAULT NULL,
  claims_city VARCHAR(100) NULL DEFAULT NULL,
  claims_state VARCHAR(100) NULL DEFAULT NULL,
  benefits_number VARCHAR(25) NULL DEFAULT NULL,
  preauthorization_number VARCHAR(25) NULL DEFAULT NULL,
  preadmission_number VARCHAR(25) NULL DEFAULT NULL,
  fax_number VARCHAR(25) NULL DEFAULT NULL,
  insurance_email VARCHAR(100) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE phr.carenotebook_specialtyclinic (
  specialtyclinic_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  specialtyclinic_name VARCHAR(50) NULL DEFAULT NULL,
  specialtyclinic_physician VARCHAR(50) NULL DEFAULT NULL,
  specialtyclinic_contact VARCHAR(50) NULL DEFAULT NULL,
  phone_number VARCHAR(25) NULL DEFAULT NULL,
  fax_number VARCHAR(25) NULL DEFAULT NULL,
  specialtyclinic_email VARCHAR(100) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_pharmacies (
  pharmacy_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pharmacy_name VARCHAR(50) NULL DEFAULT NULL,
  pharmacy_contact VARCHAR(50) NULL DEFAULT NULL,
  phone_number VARCHAR(25) NULL DEFAULT NULL,
  fax_number VARCHAR(25) NULL DEFAULT NULL,
  pharmacy_email VARCHAR(100) NULL DEFAULT NULL,
  pharmacy_medications VARCHAR(512) NULL DEFAULT NULL ,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_careproviders (
  provider_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  provider_type VARCHAR(50) NULL DEFAULT NULL,
  provider_name VARCHAR(50) NULL DEFAULT NULL,
  provider_contact VARCHAR(50) NULL DEFAULT NULL,
  provider_agency VARCHAR(50) NULL DEFAULT NULL,
  provider_address VARCHAR(200) NULL DEFAULT NULL,
  provider_address2 VARCHAR(200) NULL DEFAULT NULL,
  provider_city VARCHAR(100) NULL DEFAULT NULL,
  provider_state VARCHAR(100) NULL DEFAULT NULL,
  phone_number VARCHAR(25) NULL DEFAULT NULL,
  fax_number VARCHAR(25) NULL DEFAULT NULL,
  provider_email VARCHAR(100) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_familymember (
  familymember_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  familymember_type VARCHAR(50) NULL DEFAULT NULL,
  familymember_name VARCHAR(50) NULL DEFAULT NULL,
  familymember_address VARCHAR(200) NULL DEFAULT NULL,
  familymember_address2 VARCHAR(200) NULL DEFAULT NULL,
  familymember_city VARCHAR(100) NULL DEFAULT NULL,
  familymember_state VARCHAR(100) NULL DEFAULT NULL,
  daytime_phone_number VARCHAR(25) NULL DEFAULT NULL,
  evening_phone_number VARCHAR(25) NULL DEFAULT NULL,
  fax_number VARCHAR(25) NULL DEFAULT NULL,
  familymember_email VARCHAR(100) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_familyhistory (
  familyhistory_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  mental_illness TINYINT(1) NULL DEFAULT FALSE,
  who_mental_illness VARCHAR(50) NULL DEFAULT NULL,
  cerebral_palsy TINYINT(1) NULL DEFAULT FALSE,
  who_cerebral_palsy VARCHAR(50) NULL DEFAULT NULL,
  muscular_dystrophy TINYINT(1) NULL DEFAULT FALSE,
  who_muscular_dystrophy VARCHAR(50) NULL DEFAULT NULL,
  epilepsy TINYINT(1) NULL DEFAULT FALSE,
  who_epilepsy VARCHAR(50) NULL DEFAULT NULL,
  heart_disease TINYINT(1) NULL DEFAULT FALSE,
  who_heart_disease VARCHAR(50) NULL DEFAULT NULL,
  diabetes TINYINT(1) NULL DEFAULT FALSE,
  who_diabetes VARCHAR(50) NULL DEFAULT NULL,
  kidney_disease TINYINT(1) NULL DEFAULT FALSE,
  who_kidney_disease VARCHAR(50) NULL DEFAULT NULL,
  cancer TINYINT(1) NULL DEFAULT FALSE,
  who_cancer VARCHAR(50) NULL DEFAULT NULL,
  thyroid_disease TINYINT(1) NULL DEFAULT FALSE,
  who_thyroid_disease VARCHAR(50) NULL DEFAULT NULL,
  high_blood_pressure TINYINT(1) NULL DEFAULT FALSE,
  who_high_blood_pressure VARCHAR(50) NULL DEFAULT NULL,
  deceased_siblings TINYINT(1) NULL DEFAULT FALSE,
  who_deceased_siblings VARCHAR(50) NULL DEFAULT NULL,
  behavior_disorder TINYINT(1) NULL DEFAULT FALSE,
  who_behavior_disorder VARCHAR(50) NULL DEFAULT NULL,
  tuberculosis TINYINT(1) NULL DEFAULT FALSE,
  who_tuberculosis VARCHAR(50) NULL DEFAULT NULL,
  hepatitis TINYINT(1) NULL DEFAULT FALSE,
  who_hepatitis VARCHAR(50) NULL DEFAULT NULL,
  metabolic_disease TINYINT(1) NULL DEFAULT FALSE,
  who_metabolic_disease VARCHAR(50) NULL DEFAULT NULL,
  allergies TINYINT(1) NULL DEFAULT FALSE,
  who_allergies VARCHAR(50) NULL DEFAULT NULL,
  developmental_disabilities TINYINT(1) NULL DEFAULT FALSE,
  who_developmental_disabilities VARCHAR(50) NULL DEFAULT NULL,
  traumatic_brain_injury TINYINT(1) NULL DEFAULT FALSE,
  who_traumatic_brain_injury VARCHAR(50) NULL DEFAULT NULL,
  other VARCHAR(512) NULL DEFAULT NULL ,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_nutrition (
  nutrition_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  feeding_schedule VARCHAR(200) NULL DEFAULT NULL,
  food_likes VARCHAR(200) NULL DEFAULT NULL,
  food_dislikes VARCHAR(200) NULL DEFAULT NULL,
  feeding_modifications VARCHAR(200) NULL DEFAULT NULL,
  food_allergies VARCHAR(200) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_respiratorycare (
  respiratorycare_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  observed_date DATE NULL DEFAULT NULL,
  respiratorycare_text VARCHAR(3000) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_communication (
  communication_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  observed_date DATE NULL DEFAULT NULL,
  communication_text VARCHAR(3000) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_mobility (
  mobility_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  observed_date DATE NULL DEFAULT NULL,
  mobility_text VARCHAR(3000) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_rest (
  rest_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  observed_date DATE NULL DEFAULT NULL,
  rest_text VARCHAR(3000) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_social (
  social_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  observed_date DATE NULL DEFAULT NULL,
  social_text VARCHAR(3000) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_stress (
  stress_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  observed_date DATE NULL DEFAULT NULL,
  stress_text VARCHAR(3000) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_transition (
  transition_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  transition_text VARCHAR(3000) NULL DEFAULT NULL,
  hopes_text VARCHAR(3000) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_employment (
  employment_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  employment_place VARCHAR(50) NULL DEFAULT NULL,
  employment_start DATE NULL DEFAULT NULL,
  employment_end DATE NULL DEFAULT NULL,
  employment_supervisor VARCHAR(200) NULL DEFAULT NULL,
  employment_address VARCHAR(200) NULL DEFAULT NULL,
  employment_address2 VARCHAR(200) NULL DEFAULT NULL,
  employment_city VARCHAR(100) NULL DEFAULT NULL,
  employment_state VARCHAR(100) NULL DEFAULT NULL,
  phone_number VARCHAR(25) NULL DEFAULT NULL,
  fax_number VARCHAR(25) NULL DEFAULT NULL,
  employment_email VARCHAR(100) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_school (
  school_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  school_name VARCHAR(50) NULL DEFAULT NULL,
  school_address VARCHAR(200) NULL DEFAULT NULL,
  school_address2 VARCHAR(200) NULL DEFAULT NULL,
  school_city VARCHAR(100) NULL DEFAULT NULL,
  school_state VARCHAR(100) NULL DEFAULT NULL,
  school_phone_number VARCHAR(25) NULL DEFAULT NULL,
  school_fax_number VARCHAR(25) NULL DEFAULT NULL,
  school_email VARCHAR(100) NULL DEFAULT NULL,
  bus_garage_number VARCHAR(25) NULL DEFAULT NULL,
  bus_id_number VARCHAR(25) NULL DEFAULT NULL,
  district_name VARCHAR(50) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_school_personnel (
  personnel_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  personnel_type VARCHAR(50) NULL DEFAULT NULL,
  personnel_name VARCHAR(50) NULL DEFAULT NULL,
  personnel_address VARCHAR(200) NULL DEFAULT NULL,
  personnel_address2 VARCHAR(200) NULL DEFAULT NULL,
  personnel_city VARCHAR(100) NULL DEFAULT NULL,
  personnel_state VARCHAR(100) NULL DEFAULT NULL,
  daytime_phone_number VARCHAR(25) NULL DEFAULT NULL,
  evening_phone_number VARCHAR(25) NULL DEFAULT NULL,
  fax_number VARCHAR(25) NULL DEFAULT NULL,
  personnel_email VARCHAR(100) NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.carenotebook_meeting (
  meeting_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  meeting_date DATE NULL DEFAULT NULL,
  meeting_purpose VARCHAR(200) NULL DEFAULT NULL,
  meeting_issues VARCHAR(3000) NULL DEFAULT NULL,
  meeting_responses VARCHAR(3000) NULL DEFAULT NULL,
  meeting_outcome VARCHAR(3000) NULL DEFAULT NULL,
  meeting_steps VARCHAR(3000) NULL DEFAULT NULL,
  meeting_remember VARCHAR(3000) NULL DEFAULT NULL,
  next_meeting_date DATE NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL ,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.user_preferences (
  preference_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  show_carenotebook TINYINT(1) DEFAULT 0,
  last_updated_user VARCHAR(64) NOT NULL,
  last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE rec_health_records ADD preference_id BIGINT; -- error on subsequent runs
ALTER TABLE rec_health_records ADD CONSTRAINT fk_preference_id FOREIGN KEY (preference_id) REFERENCES user_preferences(preference_id); -- error on subsequent runs

-- Care notebook concluded

-- Caretaker values included

CREATE TABLE phr.user_users_rec_healthrecord (
  user_id_rec_id_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  rec_id INT NOT NULL,
  user_id INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE phr.rec_healthrecord_requests (
    request_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rec_id_requested INT NOT NULL,
    user_id_requestor INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Caretakers values concluded

CREATE TABLE rec_ccr_problems (ccr_problem_id BIGINT NOT NULL AUTO_INCREMENT, status_text VARCHAR(255), notes VARCHAR(255), 
	type_text VARCHAR(255), description_code_value VARCHAR(255), date_time_resolved DATETIME, description_code_codingsystem VARCHAR(255), 
	description_text VARCHAR(255), data_object_id VARCHAR(255), ccr_id BIGINT, PRIMARY KEY (ccr_problem_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_document_vitalsign_results (ccr_id BIGINT NOT NULL, ccr_vitalsign_result_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_id, ccr_vitalsign_result_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_result_tests_datetime (ccr_results_test_id BIGINT NOT NULL, datetime_id BIGINT NOT NULL,
	PRIMARY KEY (ccr_results_test_id, datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_problems_datetime (ccr_problem_id BIGINT NOT NULL, datetime_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_problem_id, datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_resource_credentials_map (resource_id BIGINT NOT NULL, credential_id BIGINT NOT NULL, 
	PRIMARY KEY (resource_id, credential_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_result_results (ccr_results_result_id BIGINT NOT NULL AUTO_INCREMENT, laboratory_name VARCHAR(255), 
	notes VARCHAR(255), description_text VARCHAR(255), ordered_by VARCHAR(255), description_coding_system VARCHAR(255), 
		descriptionCode VARCHAR(255), data_object_id VARCHAR(255), ccr_id BIGINT, PRIMARY KEY (ccr_results_result_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE data_emergencycontacts (emergencycontact_id BIGINT NOT NULL AUTO_INCREMENT, relationship VARCHAR(100) NOT NULL, 
	organization VARCHAR(100), middle_name VARCHAR(50), note VARCHAR(255), first_name VARCHAR(100) NOT NULL, last_name VARCHAR(50) NOT NULL, 
	PRIMARY KEY (emergencycontact_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_datetime (datetime_id BIGINT NOT NULL AUTO_INCREMENT, exact_date_time DATETIME, approx_date_time VARCHAR(255), 
	type_text VARCHAR(255), rangeDateTime VARCHAR(255), PRIMARY KEY (datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_resource_credentials (credential_id BIGINT NOT NULL AUTO_INCREMENT, password VARCHAR(255), last_updated DATETIME, 
	username VARCHAR(255), notes VARCHAR(255), resource_id BIGINT, PRIMARY KEY (credential_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_medications (ccr_medication_id BIGINT NOT NULL AUTO_INCREMENT, data_object_id VARCHAR(255), status VARCHAR(255), 
	product_name VARCHAR(255), form_text VARCHAR(255), product_strength_value VARCHAR(255), concentration_value VARCHAR(255), 
	direction_route_text VARCHAR(255), concentration_units_unit VARCHAR(255), date_time_stopped DATETIME, 
	quantity_value VARCHAR(255), quantity_units_unit VARCHAR(255), product_strength_units VARCHAR(255), refills_refill_number VARCHAR(255), 
	direction_dose VARCHAR(255), notes VARCHAR(255), date_time_started DATETIME, type VARCHAR(255), ccr_id BIGINT, 
	PRIMARY KEY (ccr_medication_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_alerts_datetime (ccr_alert_id BIGINT NOT NULL, datetime_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_alert_id, datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_alerts (ccr_alert_id BIGINT NOT NULL  AUTO_INCREMENT, product_name VARCHAR(255), description_text VARCHAR(255),
	reaction_description VARCHAR(255), product_description VARCHAR(255), reaction_severity VARCHAR(255), 
	data_object_id VARCHAR(255), notes VARCHAR(255), environmental_agent_description VARCHAR(255), 
	ccr_id BIGINT, PRIMARY KEY (ccr_alert_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_medications_datetime (ccr_medication_id BIGINT NOT NULL, 
	datetime_id BIGINT NOT NULL, PRIMARY KEY (ccr_medication_id, datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_result_results_tests (ccr_results_result_id BIGINT NOT NULL, 
	ccr_results_test_id BIGINT NOT NULL, PRIMARY KEY (ccr_results_result_id, ccr_results_test_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_documents (ccr_id BIGINT NOT NULL AUTO_INCREMENT, version VARCHAR(255), 
	ccr_document_size INTEGER, ccr_document_id VARCHAR(255), added_date_time DATETIME, last_updated_date_time DATETIME, 
	created_date_time DATETIME, notes VARCHAR(255), purpose_description_text VARCHAR(255), BODY LONGBLOB NOT NULL, DESCRIPTION VARCHAR(255), 
	rec_id BIGINT, PRIMARY KEY (ccr_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_vitalsign_results (ccr_vitalsign_result_id BIGINT NOT NULL AUTO_INCREMENT, 
	descriptionCode VARCHAR(255), description_coding_system VARCHAR(255), data_object_id VARCHAR(255), 
	description_text VARCHAR(255), ccr_id BIGINT, PRIMARY KEY (ccr_vitalsign_result_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_vitalsign_tests (ccr_vitalsign_test_id BIGINT NOT NULL AUTO_INCREMENT, 
	internal_result_name VARCHAR(255), testresult_value VARCHAR(255), data_object_id VARCHAR(255), 
	testresult_units_unit VARCHAR(255), description_code_value VARCHAR(255), normalresult_normal_description_text VARCHAR(255), 
	display_text VARCHAR(255), flag_text VARCHAR(255), description_text VARCHAR(255), notes VARCHAR(255), description_coding_system VARCHAR(255), 
	ccr_id BIGINT, PRIMARY KEY (ccr_vitalsign_test_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE data_emergencycontacts_phone (contactphone_id BIGINT NOT NULL, phone_number VARCHAR(50) NOT NULL, 
	phone_number_description VARCHAR(100) NOT NULL, emergencycontact_id BIGINT, PRIMARY KEY (contactphone_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_result_tests (ccr_results_test_id BIGINT NOT NULL AUTO_INCREMENT, 
	internal_result_name VARCHAR(255), testresult_value VARCHAR(255), data_object_id VARCHAR(255), 
	testresult_units_unit VARCHAR(255), description_text VARCHAR(255), normalresult_normal_description_text VARCHAR(255), 
	description_coding_system VARCHAR(255), flag_text VARCHAR(255), display_text VARCHAR(255), notes VARCHAR(255), 
	description_code_value VARCHAR(255), ccr_id BIGINT, PRIMARY KEY (ccr_results_test_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_vitalsign_results_datetime (ccr_vitalsign_result_id BIGINT NOT NULL, datetime_id BIGINT NOT NULL,
	PRIMARY KEY (ccr_vitalsign_result_id, datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_document_health_records (ccr_document_id BIGINT NOT NULL, rec_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_document_id, rec_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE sys_resource_endpoints (ep_id BIGINT NOT NULL AUTO_INCREMENT, retry_count_limit INTEGER, added_date_time DATETIME, 
	timeout_value BIGINT, last_updated_date_time DATETIME, notes VARCHAR(255), display_name VARCHAR(255), RESOURCE_resource_id BIGINT, 
	PRIMARY KEY (ep_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_vitalsign_results_tests (ccr_vitalsign_result_id BIGINT NOT NULL, ccr_vitalsign_test_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_vitalsign_result_id, ccr_vitalsign_test_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_document_medications (ccr_id BIGINT NOT NULL, ccr_medication_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_id, ccr_medication_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_result_results_datetime (ccr_results_result_id BIGINT NOT NULL, datetime_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_results_result_id, datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- CREATE TABLE rec_ccr_document_problems (ccr_id BIGINT NOT NULL, ccr_problem_id BIGINT NOT NULL, PRIMARY KEY (ccr_id, ccr_problem_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_actors (ccr_actor_id BIGINT NOT NULL AUTO_INCREMENT, person_dob_exactdatetime DATETIME, 
	person_gender_text VARCHAR(255), ccr_actorlink_actorid VARCHAR(255), information_system_name VARCHAR(255), actor_object_id VARCHAR(255), 
	information_system_version VARCHAR(255), person_name_currentname_family VARCHAR(255), ccr_actor_type VARCHAR(255),
	person_name_currentname_middle VARCHAR(255), ccr_tree_node VARCHAR(255), specialty_text VARCHAR(255), 
	person_name_currentname_given VARCHAR(255), source_actor_id VARCHAR(255), person_name_currentname_title VARCHAR(255), source_map_id BIGINT,
	PRIMARY KEY (ccr_actor_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_resource_stats (resource_stat_id BIGINT NOT NULL AUTO_INCREMENT, rec_id BIGINT NOT NULL, 
	flag_error TINYINT(1) default 0, processing_duration_time BIGINT, flag_update_resource TINYINT(1) default 0, 
	retrieval_duration_time BIGINT, notes VARCHAR(255), latest_update_request_source DATETIME,  
	PRIMARY KEY (resource_stat_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_immunizations (ccr_immunization_id BIGINT NOT NULL AUTO_INCREMENT, product_productname_code_value VARCHAR(255), 
	product_productname_code_codingsystem VARCHAR(255), product_productname_text VARCHAR(255), notes VARCHAR(255), 
	data_object_id VARCHAR(255), ccr_id BIGINT, PRIMARY KEY (ccr_immunization_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- CREATE TABLE rec_ccr_vitalsign_result_sources (ccr_vitalsign_result_id BIGINT NOT NULL, ccr_actor_id BIGINT NOT NULL, PRIMARY KEY (ccr_vitalsign_result_id, ccr_actor_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_immunizations_datetime (ccr_immunization_id BIGINT NOT NULL, datetime_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_immunization_id, datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_document_alerts (ccr_id BIGINT NOT NULL, ccr_alert_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_id, ccr_alert_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE data_emergencycontacts_address (contactaddress_id BIGINT NOT NULL, state VARCHAR(100), 
	province VARCHAR(100), address2 VARCHAR(200), zip VARCHAR(25), address1 VARCHAR(200), emergencycontact_id BIGINT, 
	city VARCHAR(100), PRIMARY KEY (contactaddress_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_document_results (ccr_id BIGINT NOT NULL, ccr_results_result_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_id, ccr_results_result_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_document_actors (ccr_id BIGINT NOT NULL, ccr_actor_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_id, ccr_actor_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_record_identifiers (identity_id BIGINT NOT NULL AUTO_INCREMENT, rec_id BIGINT NOT NULL,
	identifier_value VARCHAR(100) NOT NULL, notes VARCHAR(255), identifier_name VARCHAR(100) NOT NULL, resource_id BIGINT NOT NULL,
	PRIMARY KEY (identity_id),
        UNIQUE KEY rec_record_identifiers_uk (identifier_value, identifier_name, resource_id)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_document_immunizations (ccr_id BIGINT NOT NULL, ccr_immunization_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_id, ccr_immunization_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_resources (resource_id BIGINT NOT NULL AUTO_INCREMENT,  active tinyint(1) DEFAULT 1,  type VARCHAR(255),
        display_name VARCHAR(255), resource_location_path VARCHAR(255), resource_namespace VARCHAR(255),
        resource_local_part VARCHAR(255), added_date_time DATETIME, last_updated_date_time DATETIME,
	PRIMARY KEY (resource_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_documents_datetime (ccr_document_id BIGINT NOT NULL, datetime_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_document_id, datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_vitalsign_tests_datetime (ccr_vitalsign_test_id BIGINT NOT NULL, datetime_id BIGINT NOT NULL, 
	PRIMARY KEY (ccr_vitalsign_test_id, datetime_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_health_records_record_identifiers (rec_id BIGINT NOT NULL, identity_id BIGINT NOT NULL, 
	PRIMARY KEY (rec_id, identity_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_health_records_resource_stats (rec_id BIGINT NOT NULL, resource_stat_id BIGINT NOT NULL, 
	PRIMARY KEY (rec_id, resource_stat_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- sources 
CREATE TABLE rec_ccr_result_results_sources (source_map_id BIGINT NOT NULL AUTO_INCREMENT, ccr_actor_id BIGINT , ccr_tree_node VARCHAR(255), 
	source_description_text VARCHAR(1024), ccr_results_result_id BIGINT , DTYPE VARCHAR(64) DEFAULT 'CcrResultSource', 
	PRIMARY KEY (source_map_id )) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_immunization_sources (source_map_id BIGINT NOT NULL AUTO_INCREMENT, ccr_actor_id BIGINT , ccr_tree_node VARCHAR(255), 
	source_description_text VARCHAR(1024), ccr_immunization_id BIGINT , DTYPE VARCHAR(64) DEFAULT 'CcrImmunizationSource', 
	PRIMARY KEY (source_map_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_result_test_sources (source_map_id BIGINT NOT NULL AUTO_INCREMENT, ccr_actor_id BIGINT , ccr_tree_node VARCHAR(255), 
	source_description_text VARCHAR(1024), ccr_results_test_id BIGINT , DTYPE VARCHAR(64) DEFAULT 'CcrResultTestSource', 
	PRIMARY KEY (source_map_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_problem_sources (source_map_id BIGINT NOT NULL AUTO_INCREMENT, ccr_actor_id BIGINT , ccr_tree_node VARCHAR(255), 
	source_description_text VARCHAR(1024) ,ccr_problem_id BIGINT, DTYPE VARCHAR(64) DEFAULT 'CcrProblemSource', 
	PRIMARY KEY (source_map_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_vitalsign_test_sources (source_map_id BIGINT NOT NULL AUTO_INCREMENT,  ccr_actor_id BIGINT , ccr_tree_node VARCHAR(255), 
	source_description_text VARCHAR(1024), ccr_vitalsign_test_id BIGINT ,DTYPE VARCHAR(64) DEFAULT 'CcrVitalSignTestSource', 
	PRIMARY KEY (source_map_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_vitalsign_result_sources (source_map_id BIGINT NOT NULL AUTO_INCREMENT,  ccr_actor_id BIGINT , ccr_tree_node VARCHAR(255), 
	source_description_text VARCHAR(1024), ccr_vitalsign_result_id BIGINT ,  DTYPE VARCHAR(64) DEFAULT 'CcrVitalSignResultSource', 
	PRIMARY KEY (source_map_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_alert_sources (source_map_id BIGINT NOT NULL AUTO_INCREMENT, ccr_actor_id BIGINT , ccr_tree_node VARCHAR(255), 
	source_description_text VARCHAR(1024), ccr_alert_id BIGINT ,DTYPE VARCHAR(64) DEFAULT 'CcrAlertSource', 
	PRIMARY KEY (source_map_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE rec_ccr_medication_sources (source_map_id BIGINT NOT NULL AUTO_INCREMENT,  ccr_actor_id BIGINT , ccr_tree_node VARCHAR(255), 
	source_description_text VARCHAR(1024), ccr_medication_id BIGINT , DTYPE VARCHAR(64) DEFAULT 'CcrMedicationSource', 
	PRIMARY KEY (source_map_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE rec_ccr_document_vitalsign_results ADD CONSTRAINT recccrdocumentvitalsignresultsccrvitalsignresultid FOREIGN KEY (ccr_vitalsign_result_id) REFERENCES rec_ccr_vitalsign_results (ccr_vitalsign_result_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_vitalsign_results ADD CONSTRAINT FK_rec_ccr_document_vitalsign_results_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_result_tests_datetime ADD CONSTRAINT FK_rec_ccr_result_tests_datetime_datetime_id FOREIGN KEY (datetime_id) REFERENCES rec_ccr_datetime (datetime_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_result_tests_datetime ADD CONSTRAINT FK_rec_ccr_result_tests_datetime_ccr_results_test_id FOREIGN KEY (ccr_results_test_id) REFERENCES rec_ccr_result_tests (ccr_results_test_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_problems_datetime ADD CONSTRAINT FK_rec_ccr_problems_datetime_datetime_id FOREIGN KEY (datetime_id) REFERENCES rec_ccr_datetime (datetime_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_problems_datetime ADD CONSTRAINT FK_rec_ccr_problems_datetime_ccr_problem_id FOREIGN KEY (ccr_problem_id) REFERENCES rec_ccr_problems (ccr_problem_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_result_test_sources ADD CONSTRAINT FK_rec_ccr_result_test_sources_ccr_actor_id FOREIGN KEY (ccr_actor_id) REFERENCES rec_ccr_actors (ccr_actor_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_result_test_sources ADD CONSTRAINT FK_rec_ccr_result_test_sources_ccr_results_test_id FOREIGN KEY (ccr_results_test_id) REFERENCES rec_ccr_result_tests (ccr_results_test_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_result_results_sources ADD CONSTRAINT FK_rec_ccr_result_result_sources_ccr_actor_id FOREIGN KEY (ccr_actor_id) REFERENCES rec_ccr_actors (ccr_actor_id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE rec_ccr_result_results_sources ADD CONSTRAINT FK_rec_ccr_result_result_sources_ccr_results_result_id FOREIGN KEY (ccr_results_result_id) REFERENCES rec_ccr_result_results (ccr_results_result_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rec_resource_credentials_map ADD CONSTRAINT FK_rec_resource_credentials_map_resource_id FOREIGN KEY (resource_id) REFERENCES rec_resources (resource_id) ON DELETE CASCADE;
ALTER TABLE rec_resource_credentials_map ADD CONSTRAINT FK_rec_resource_credentials_map_credential_id FOREIGN KEY (credential_id) REFERENCES rec_resource_credentials (credential_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_result_results ADD CONSTRAINT FK_rec_ccr_result_results_RCD_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;

ALTER TABLE rec_resource_credentials ADD CONSTRAINT FK_rec_resource_credentials_resource_id FOREIGN KEY (resource_id) REFERENCES rec_resources (resource_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_medications ADD CONSTRAINT FK_rec_ccr_medications_ccr_documents_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_alerts_datetime ADD CONSTRAINT FK_rec_ccr_alerts_datetime_datetime_id FOREIGN KEY (datetime_id) REFERENCES rec_ccr_datetime (datetime_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_alerts_datetime ADD CONSTRAINT FK_rec_ccr_alerts_datetime_ccr_alert_id FOREIGN KEY (ccr_alert_id) REFERENCES rec_ccr_alerts (ccr_alert_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_medications_datetime ADD CONSTRAINT FK_rec_ccr_medications_datetime_ccr_medication_id FOREIGN KEY (ccr_medication_id) REFERENCES rec_ccr_medications (ccr_medication_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_medications_datetime ADD CONSTRAINT FK_rec_ccr_medications_datetime_datetime_id FOREIGN KEY (datetime_id) REFERENCES rec_ccr_datetime (datetime_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_result_results_tests ADD CONSTRAINT rec_ccr_result_results_tests_ccr_results_result_id FOREIGN KEY (ccr_results_result_id) REFERENCES rec_ccr_result_results (ccr_results_result_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_result_results_tests ADD CONSTRAINT FK_rec_ccr_result_results_tests_ccr_results_test_id FOREIGN KEY (ccr_results_test_id) REFERENCES rec_ccr_result_tests (ccr_results_test_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_vitalsign_results ADD CONSTRAINT FK_rec_ccr_vitalsign_results_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_vitalsign_tests ADD CONSTRAINT FK_rec_ccr_vitalsign_tests_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;

ALTER TABLE data_emergencycontacts_phone ADD CONSTRAINT FK_data_emergencycontacts_phone_emergencycontact_id FOREIGN KEY (emergencycontact_id) REFERENCES data_emergencycontacts (emergencycontact_id) ON DELETE CASCADE;


ALTER TABLE rec_ccr_result_tests ADD CONSTRAINT FK_rec_ccr_result_tests_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_vitalsign_results_datetime ADD CONSTRAINT recccrvitalsignresultsdatetimeccrvitalsignresultid FOREIGN KEY (ccr_vitalsign_result_id) REFERENCES rec_ccr_vitalsign_results (ccr_vitalsign_result_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_vitalsign_results_datetime ADD CONSTRAINT FK_rec_ccr_vitalsign_results_datetime_datetime_id FOREIGN KEY (datetime_id) REFERENCES rec_ccr_datetime (datetime_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_document_health_records ADD CONSTRAINT FK_rec_ccr_document_health_records_ccr_document_id FOREIGN KEY (ccr_document_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_health_records ADD CONSTRAINT FK_rec_ccr_document_health_records_rec_id FOREIGN KEY (rec_id) REFERENCES rec_health_records (rec_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_problem_sources ADD CONSTRAINT FK_rec_ccr_problem_sources_ccr_actor_id FOREIGN KEY (ccr_actor_id) REFERENCES rec_ccr_actors (ccr_actor_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_problem_sources ADD CONSTRAINT FK_rec_ccr_problem_sources_ccr_problem_id FOREIGN KEY (ccr_problem_id) REFERENCES rec_ccr_problems (ccr_problem_id) ON DELETE CASCADE;

ALTER TABLE sys_resource_endpoints ADD CONSTRAINT FK_sys_resource_endpoints_RESOURCE_resource_id FOREIGN KEY (RESOURCE_resource_id) REFERENCES rec_resources (resource_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_vitalsign_results_tests ADD CONSTRAINT recccrvitalsign_results_testsccr_vitalsign_test_id FOREIGN KEY (ccr_vitalsign_test_id) REFERENCES rec_ccr_vitalsign_tests (ccr_vitalsign_test_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_vitalsign_results_tests ADD CONSTRAINT recccrvitalsignresultstestsccr_vitalsign_result_id FOREIGN KEY (ccr_vitalsign_result_id) REFERENCES rec_ccr_vitalsign_results (ccr_vitalsign_result_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_document_medications ADD CONSTRAINT FK_rec_ccr_document_medications_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_medications ADD CONSTRAINT FK_rec_ccr_document_medications_ccr_medication_id FOREIGN KEY (ccr_medication_id) REFERENCES rec_ccr_medications (ccr_medication_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_result_results_datetime ADD CONSTRAINT FK_rec_ccr_result_results_datetime_datetime_id FOREIGN KEY (datetime_id) REFERENCES rec_ccr_datetime (datetime_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_result_results_datetime ADD CONSTRAINT recccrresult_results_datetimeccr_results_result_id FOREIGN KEY (ccr_results_result_id) REFERENCES rec_ccr_result_results (ccr_results_result_id) ON DELETE CASCADE;
-- ALTER TABLE rec_ccr_document_problems ADD CONSTRAINT FK_rec_ccr_document_problems_ccr_problem_id FOREIGN KEY (ccr_problem_id) REFERENCES rec_ccr_problems (ccr_problem_id) ON DELETE CASCADE;
-- ALTER TABLE rec_ccr_document_problems ADD CONSTRAINT FK_rec_ccr_document_problems_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;
-- ALTER TABLE rec_ccr_vitalsign_result_sources ADD CONSTRAINT recccrvitalsignresult_sourcesccr_vitalsign_result_id FOREIGN KEY (ccr_vitalsign_result_id) REFERENCES rec_ccr_vitalsign_results (ccr_vitalsign_result_id);
-- ALTER TABLE rec_ccr_vitalsign_result_sources ADD CONSTRAINT FK_rec_ccr_vitalsign_result_sources_ccr_actor_id FOREIGN KEY (ccr_actor_id) REFERENCES rec_ccr_actors (ccr_actor_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_vitalsign_test_sources ADD CONSTRAINT FK_rec_ccr_vitalsign_test_sources_ccr_actor_id FOREIGN KEY (ccr_actor_id) REFERENCES rec_ccr_actors (ccr_actor_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_vitalsign_test_sources ADD CONSTRAINT recccr_vitalsign_test_sourcesccr_vitalsign_test_id FOREIGN KEY (ccr_vitalsign_test_id) REFERENCES rec_ccr_vitalsign_tests (ccr_vitalsign_test_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_vitalsign_result_sources ADD CONSTRAINT FK_rec_ccr_vitalsign_result_sources_ccr_actor_id FOREIGN KEY (ccr_actor_id) REFERENCES rec_ccr_actors (ccr_actor_id);
ALTER TABLE rec_ccr_vitalsign_result_sources ADD CONSTRAINT recccr_vitalsign_result_sourcesccr_vitalsign_test_id FOREIGN KEY (ccr_vitalsign_result_id) REFERENCES rec_ccr_vitalsign_results (ccr_vitalsign_result_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_immunizations_datetime ADD CONSTRAINT rec_ccr_immunizations_datetime_ccr_immunization_id FOREIGN KEY (ccr_immunization_id) REFERENCES rec_ccr_immunizations (ccr_immunization_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_immunizations_datetime ADD CONSTRAINT FK_rec_ccr_immunizations_datetime_datetime_id FOREIGN KEY (datetime_id) REFERENCES rec_ccr_datetime (datetime_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_alerts ADD CONSTRAINT FK_rec_ccr_document_alerts_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_alerts ADD CONSTRAINT FK_rec_ccr_document_alerts_ccr_alert_id FOREIGN KEY (ccr_alert_id) REFERENCES rec_ccr_alerts (ccr_alert_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_alert_sources ADD CONSTRAINT FK_rec_ccr_alert_sources_ccr_alert_id FOREIGN KEY (ccr_alert_id) REFERENCES rec_ccr_alerts (ccr_alert_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_alert_sources ADD CONSTRAINT FK_rec_ccr_alert_sources_ccr_actor_id FOREIGN KEY (ccr_actor_id) REFERENCES rec_ccr_actors (ccr_actor_id);
ALTER TABLE data_emergencycontacts_address ADD CONSTRAINT FK_data_emergencycontacts_address_emergencycontact_id FOREIGN KEY (emergencycontact_id) REFERENCES data_emergencycontacts (emergencycontact_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_results ADD CONSTRAINT FK_rec_ccr_document_results_ccr_results_result_id FOREIGN KEY (ccr_results_result_id) REFERENCES rec_ccr_result_results (ccr_results_result_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_results ADD CONSTRAINT FK_rec_ccr_document_results_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;

ALTER TABLE rec_ccr_document_actors ADD CONSTRAINT FK_rec_ccr_document_actors_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_actors ADD CONSTRAINT FK_rec_ccr_document_actors_ccr_actor_id FOREIGN KEY (ccr_actor_id) REFERENCES rec_ccr_actors (ccr_actor_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_medication_sources ADD CONSTRAINT FK_rec_ccr_medication_sources_ccr_actor_id FOREIGN KEY (ccr_actor_id) REFERENCES rec_ccr_actors (ccr_actor_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_medication_sources ADD CONSTRAINT FK_rec_ccr_medication_sources_ccr_medication_id FOREIGN KEY (ccr_medication_id) REFERENCES rec_ccr_medications (ccr_medication_id) ON DELETE CASCADE;
ALTER TABLE rec_record_identifiers ADD CONSTRAINT FK_rec_record_identifiers_resource_id FOREIGN KEY (resource_id) REFERENCES rec_resources (resource_id) ON DELETE CASCADE;
ALTER TABLE rec_record_identifiers ADD CONSTRAINT FK_rec_record_identifiers_HEALTHRECORD_rec_id FOREIGN KEY (rec_id) REFERENCES rec_health_records (rec_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_immunizations ADD CONSTRAINT FK_rec_ccr_document_immunizations_ccr_id FOREIGN KEY (ccr_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_document_immunizations ADD CONSTRAINT rec_ccr_document_immunizations_ccr_immunization_id FOREIGN KEY (ccr_immunization_id) REFERENCES rec_ccr_immunizations (ccr_immunization_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_documents_datetime ADD CONSTRAINT FK_rec_ccr_documents_datetime_datetime_id FOREIGN KEY (datetime_id) REFERENCES rec_ccr_datetime (datetime_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_documents_datetime ADD CONSTRAINT FK_rec_ccr_documents_datetime_ccr_document_id FOREIGN KEY (ccr_document_id) REFERENCES rec_ccr_documents (ccr_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_vitalsign_tests_datetime ADD CONSTRAINT FK_rec_ccr_vitalsign_tests_datetime_datetime_id FOREIGN KEY (datetime_id) REFERENCES rec_ccr_datetime (datetime_id) ON DELETE CASCADE;
ALTER TABLE rec_ccr_vitalsign_tests_datetime ADD CONSTRAINT recccrvitalsigntests_datetimeccr_vitalsign_test_id FOREIGN KEY (ccr_vitalsign_test_id) REFERENCES rec_ccr_vitalsign_tests (ccr_vitalsign_test_id) ON DELETE CASCADE;
ALTER TABLE rec_resource_stats ADD CONSTRAINT FK_rec_resource_stat_health_records_rec_id FOREIGN KEY (rec_id) REFERENCES rec_health_records (rec_id) ON DELETE CASCADE;
ALTER TABLE rec_health_records_record_identifiers ADD CONSTRAINT FK_rec_health_records_record_identifiers_rec_id FOREIGN KEY (rec_id) REFERENCES rec_health_records (rec_id) ON DELETE CASCADE;
ALTER TABLE rec_health_records_record_identifiers ADD CONSTRAINT FK_rec_health_records_record_identifiers_identity_id FOREIGN KEY (identity_id) REFERENCES rec_record_identifiers (identity_id) ON DELETE CASCADE;
ALTER TABLE rec_health_records_resource_stats ADD CONSTRAINT FK_rec_health_records_resource_stats_rec_id FOREIGN KEY (rec_id) REFERENCES rec_health_records (rec_id) ON DELETE CASCADE;
ALTER TABLE rec_health_records_resource_stats ADD CONSTRAINT FK_rec_health_records_resource_stats_resource_stat_id FOREIGN KEY (resource_stat_id) REFERENCES rec_resource_stats (resource_stat_id) ON DELETE CASCADE;

delimiter |

CREATE TRIGGER permeate_ienidentifier_oninsert AFTER INSERT ON phr.rec_record_identifiers
  FOR EACH ROW BEGIN
    IF NEW.identifier_name = 'IEN' THEN
      INSERT INTO phr.rec_resource_stats (rec_id, flag_update_resource) VALUES (NEW.rec_id, 1);
      INSERT INTO phr.rec_health_records_record_identifiers (rec_id, identity_id) VALUES (NEW.rec_id, NEW.identity_id);
    END IF;
  END;
|

CREATE TRIGGER permeate_statidentifier_oninsert AFTER INSERT ON phr.rec_resource_stats
  FOR EACH ROW BEGIN
    INSERT INTO phr.rec_health_records_resource_stats (rec_id, resource_stat_id) VALUES (NEW.rec_id, NEW.resource_stat_id);
  END;
|

delimiter ;