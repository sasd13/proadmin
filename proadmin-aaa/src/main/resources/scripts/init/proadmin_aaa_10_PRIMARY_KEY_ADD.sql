USE proadmin_aaa;

ALTER TABLE credentials ADD CONSTRAINT pk_cr PRIMARY KEY (_username);

ALTER TABLE profiles ADD CONSTRAINT pk_pr PRIMARY KEY (_id);

COMMIT;