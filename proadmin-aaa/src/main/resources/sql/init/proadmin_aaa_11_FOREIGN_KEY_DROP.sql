USE proadmin_aaa;

ALTER TABLE userpreferences DROP FOREIGN KEY fk_usr;
ALTER TABLE userpreferences DROP FOREIGN KEY fk_prf;

COMMIT