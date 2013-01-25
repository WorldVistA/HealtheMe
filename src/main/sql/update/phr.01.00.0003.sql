START TRANSACTION;

-- officially install this version of the db assuming everything executed correctly

INSERT INTO phr.schema_changes (major_release_number, minor_release_number, point_release_number, script_name, date_applied) VALUES (
	"01",
	"00",
	"0003",
	'remove duplicated columns for nutrition info of carenotebook',
	NOW()
);

ALTER TABLE phr.carenotebook_nutrition DROP COLUMN food_allergies;

COMMIT;
