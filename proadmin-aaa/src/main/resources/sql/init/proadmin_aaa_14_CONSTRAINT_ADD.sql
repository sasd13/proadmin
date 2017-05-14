USE proadmin_aaa;

ALTER TABLE users ADD CONSTRAINT uq_usr UNIQUE (_username);

COMMIT