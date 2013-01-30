START TRANSACTION;

-- officially install this version of the db assuming everything executed correctly

INSERT INTO phr.schema_changes (major_release_number, minor_release_number, point_release_number, script_name, date_applied) VALUES (
	"01",
	"00",
	"0004",
	'add new section for nutrition events sublist',
	NOW()
);

CREATE TABLE phr.carenotebook_nutritionevent (
  event_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  event VARCHAR(200) NULL DEFAULT NULL,
  event_date DATE NULL DEFAULT NULL,
  rec_id BIGINT UNSIGNED NOT NULL,
  data_source_id BIGINT(20) UNSIGNED NOT NULL ,
  care_document_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  source_id BIGINT(20) UNSIGNED NULL DEFAULT NULL ,
  date_added TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  comments VARCHAR(512) NULL DEFAULT NULL,
  mask VARCHAR(50) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;;

INSERT INTO phr.carenotebook_nutritionevent (event,event_date,rec_id,data_source_id,care_document_id, source_id, date_added, comments, mask)
SELECT feeding_modifications, null, rec_id, data_source_id, care_document_id, source_id, date_added, comments, mask
FROM phr.carenotebook_nutrition

ALTER TABLE phr.carenotebook_nutrition DROP COLUMN feeding_modifications;

COMMIT;
