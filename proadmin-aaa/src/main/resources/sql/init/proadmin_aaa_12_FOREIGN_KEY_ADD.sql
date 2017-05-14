USE proadmin_aaa;

ALTER TABLE userpreferences 
	ADD CONSTRAINT fk_usr 
	FOREIGN KEY (_user) 
	REFERENCES users (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE userpreferences 
	ADD CONSTRAINT fk_prf 
	FOREIGN KEY (_preference) 
	REFERENCES preferences (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

COMMIT