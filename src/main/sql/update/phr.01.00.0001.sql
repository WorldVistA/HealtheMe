-- migration script to baseline db at 1.00.0000

START TRANSACTION;

-- officially install this version of the db assuming everything executed correctly

INSERT INTO phr.schema_changes (major_release_number, minor_release_number, point_release_number, script_name, date_applied) VALUES (
	"01",
	"00",
	"0001",
	'remove unused columns for emergency info of carenotebook',
	NOW()
);

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN emergency_description;

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN treatment_description;

COMMIT;