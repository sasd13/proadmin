SET search_path TO proadmin_db;

ALTER TABLE studentteams 
	ADD CONSTRAINT fk_sttm_st 
	FOREIGN KEY (_student) 
	REFERENCES students (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE studentteams 
	ADD CONSTRAINT fk_sttm_tm 
	FOREIGN KEY (_team) 
	REFERENCES teams (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runnings 
	ADD CONSTRAINT fk_rn_pr 
	FOREIGN KEY (_project) 
	REFERENCES projects (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runnings 
	ADD CONSTRAINT fk_rn_tc 
	FOREIGN KEY (_teacher) 
	REFERENCES teachers (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runningteams 
	ADD CONSTRAINT fk_rntm_rn 
	FOREIGN KEY (_year,_project,_teacher) 
	REFERENCES runnings (_year,_project,_teacher) 
	ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE runningteams 
	ADD CONSTRAINT fk_rntm_tm 
	FOREIGN KEY (_team) 
	REFERENCES teams (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runningteams 
	ADD CONSTRAINT fk_rntm_al 
	FOREIGN KEY (_academiclevel) 
	REFERENCES academiclevels (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reports 
	ADD CONSTRAINT fk_rp_rntm 
	FOREIGN KEY (_year,_project,_teacher,_team,_academiclevel) 
	REFERENCES runningteams (_year,_project,_teacher,_team,_academiclevel) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE leadevaluations 
	ADD CONSTRAINT fk_le_rp 
	FOREIGN KEY (_report) 
	REFERENCES reports (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE leadevaluations 
	ADD CONSTRAINT fk_le_st 
	FOREIGN KEY (_student) 
	REFERENCES students (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE individualevaluations 
	ADD CONSTRAINT fk_ie_rp 
	FOREIGN KEY (_report) 
	REFERENCES reports (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE individualevaluations 
	ADD CONSTRAINT fk_ie_st 
	FOREIGN KEY (_student) 
	REFERENCES students (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

--COMMIT;