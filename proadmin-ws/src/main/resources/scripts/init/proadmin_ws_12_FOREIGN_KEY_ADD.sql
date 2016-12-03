SET search_path TO proadmin_ws;

ALTER TABLE studentteams 
	ADD CONSTRAINT fk_sttm_st 
	FOREIGN KEY (_student_code) 
	REFERENCES students (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE studentteams 
	ADD CONSTRAINT fk_sttm_tm 
	FOREIGN KEY (_team_code) 
	REFERENCES teams (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runnings 
	ADD CONSTRAINT fk_rn_pj 
	FOREIGN KEY (_project_code) 
	REFERENCES projects (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runnings 
	ADD CONSTRAINT fk_rn_tc 
	FOREIGN KEY (_teacher_code) 
	REFERENCES teachers (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runningteams 
	ADD CONSTRAINT fk_rntm_rn 
	FOREIGN KEY (_running_year,_running_project_code,_running_teacher_code) 
	REFERENCES runnings (_year,_project_code,_teacher_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE runningteams 
	ADD CONSTRAINT fk_rntm_tm 
	FOREIGN KEY (_team_code) 
	REFERENCES teams (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runningteams 
	ADD CONSTRAINT fk_rntm_al 
	FOREIGN KEY (_academiclevel_code) 
	REFERENCES academiclevels (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reports 
	ADD CONSTRAINT fk_rp_rntm 
	FOREIGN KEY (_runningteam_running_year,_runningteam_running_project_code,_runningteam_running_teacher_code,_runningteam_team_code,_runningteam_academiclevel_code) 
	REFERENCES runningteams (_running_year,_running_project_code,_running_teacher_code,_team_code,_academiclevel_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE leadevaluations 
	ADD CONSTRAINT fk_le_rp_code 
	FOREIGN KEY (_report_code) 
	REFERENCES reports (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;
	
ALTER TABLE leadevaluations 
	ADD CONSTRAINT fk_le_st_code 
	FOREIGN KEY (_student_code) 
	REFERENCES students (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE individualevaluations 
	ADD CONSTRAINT fk_ie_rp_code 
	FOREIGN KEY (_report_code) 
	REFERENCES reports (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE individualevaluations 
	ADD CONSTRAINT fk_ie_st_code 
	FOREIGN KEY (_student_code) 
	REFERENCES students (_code) 
	ON DELETE CASCADE ON UPDATE CASCADE;

--COMMIT;