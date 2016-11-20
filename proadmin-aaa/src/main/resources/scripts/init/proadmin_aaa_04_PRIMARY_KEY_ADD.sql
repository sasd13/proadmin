USE proadmin_aaa;

ALTER TABLE credentials ADD CONSTRAINT pk_cr PRIMARY KEY (username);

COMMIT;