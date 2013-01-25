-- migration script to baseline db at 1.00.0000

START TRANSACTION;

-- db meta tables

CREATE TABLE phr.schema_changes (
	id bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	major_release_number varchar(2) NOT NULL,
	minor_release_number varchar(2) NOT NULL,
	point_release_number varchar(4) NOT NULL,
	script_name varchar(256) NOT NULL,
	date_applied TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- officially install this version of the db assuming everything executed correctly

INSERT INTO phr.schema_changes (major_release_number, minor_release_number, point_release_number, script_name, date_applied) VALUES (
	"01",
	"00",
	"0000",
	'initial schema tracking',
	NOW()
);

ALTER TABLE phr.carenotebook_familymember ADD is_primary TINYINT(1) NOT NULL DEFAULT FALSE;

COMMIT;