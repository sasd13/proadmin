SET search_path TO proadmin_ws2;

ALTER TABLE studentteams 
	ADD CONSTRAINT fk_sttm_st 
	FOREIGN KEY (_student) 
	REFERENCES students (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE studentteams 
	ADD CONSTRAINT fk_sttm_tm 
	FOREIGN KEY (_team) 
	REFERENCES teams (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runnings 
	ADD CONSTRAINT fk_rn_pr 
	FOREIGN KEY (_project) 
	REFERENCES projects (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runnings 
	ADD CONSTRAINT fk_rn_tc 
	FOREIGN KEY (_teacher) 
	REFERENCES teachers (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runningteams 
	ADD CONSTRAINT fk_rntm_rn 
	FOREIGN KEY (_running) 
	REFERENCES runnings (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE runningteams 
	ADD CONSTRAINT fk_rntm_tm 
	FOREIGN KEY (_team) 
	REFERENCES teams (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runningteams 
	ADD CONSTRAINT fk_rntm_al 
	FOREIGN KEY (_academiclevel) 
	REFERENCES academiclevels (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reports 
	ADD CONSTRAINT fk_rp_rntm 
	FOREIGN KEY (_runningteam) 
	REFERENCES runningteams (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE leadevaluations 
	ADD CONSTRAINT fk_le_rp 
	FOREIGN KEY (_report) 
	REFERENCES reports (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE leadevaluations 
	ADD CONSTRAINT fk_le_st 
	FOREIGN KEY (_student) 
	REFERENCES students (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE individualevaluations 
	ADD CONSTRAINT fk_ie_rp 
	FOREIGN KEY (_report) 
	REFERENCES reports (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE individualevaluations 
	ADD CONSTRAINT fk_ie_st 
	FOREIGN KEY (_student) 
	REFERENCES students (_id) 
	ON DELETE CASCADE ON UPDATE CASCADE;

--COMMIT;