USE proadmin_aaa;

ALTER TABLE users ADD CONSTRAINT uq_us UNIQUE (_username);

COMMIT