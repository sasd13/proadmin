USE proadmin_aaa;

ALTER TABLE profiles ADD CONSTRAINT fk_pr_username FOREIGN KEY (_username) REFERENCES credentials(_username);

COMMIT