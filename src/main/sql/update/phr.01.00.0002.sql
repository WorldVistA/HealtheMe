-- migration script to baseline db at 1.00.0000

START TRANSACTION;

-- officially install this version of the db assuming everything executed correctly

INSERT INTO phr.schema_changes (major_release_number, minor_release_number, point_release_number, script_name, date_applied) VALUES (
	"01",
	"00",
	"0001",
	'remove duplicated columns for emergency info of carenotebook',
	NOW()
);

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN physician_name;

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN physician_number;

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN mother_name;

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN mother_number;

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN father_name;

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN father_number;

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN emergency_contact_name;

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN emergency_contact_number;

ALTER TABLE phr.carenotebook_emergencyinformation DROP COLUMN emergency_contact_relationship;

COMMIT;