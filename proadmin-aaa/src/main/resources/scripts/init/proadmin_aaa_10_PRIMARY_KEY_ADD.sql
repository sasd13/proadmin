USE proadmin_aaa;

ALTER TABLE profiles ADD CONSTRAINT pk_pr PRIMARY KEY (_username);

COMMIT;