USE proadmin_aaa;

DELETE FROM preferences;

-- PREFERENCES

INSERT INTO preferences(_category,_name,_defaultvalue) VALUES ('GEN','DT','yyyy-MM-dd');

COMMIT;