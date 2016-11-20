USE proadmin_ws;

ALTER TABLE studentteams ADD CONSTRAINT fk_sttm_st_code FOREIGN KEY (student_code) REFERENCES students (code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE studentteams ADD CONSTRAINT fk_sttm_tm_code FOREIGN KEY (team_code) REFERENCES teams (code) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runnings ADD CONSTRAINT fk_rn_pj_code FOREIGN KEY (project_code) REFERENCES projects (code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE runnings ADD CONSTRAINT fk_rn_tc_code FOREIGN KEY (teacher_code) REFERENCES teachers (code) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE runningteams ADD CONSTRAINT fk_rntm_rn_year FOREIGN KEY (running_year) REFERENCES runnings (year) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE runningteams ADD CONSTRAINT fk_rntm_rn_pj_code FOREIGN KEY (running_project_code) REFERENCES runnings (project_code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE runningteams ADD CONSTRAINT fk_rntm_rn_tc_code FOREIGN KEY (running_teacher_code) REFERENCES runnings (teacher_code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE runningteams ADD CONSTRAINT fk_rntm_tm_code FOREIGN KEY (team_code) REFERENCES teams (code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE runningteams ADD CONSTRAINT fk_rntm_al_code FOREIGN KEY (academiclevel_code) REFERENCES academiclevels (code) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reports ADD CONSTRAINT fk_rp_rntm_rn_year FOREIGN KEY (runningteam_running_year) REFERENCES runningteams (running_year) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE reports ADD CONSTRAINT fk_rp_rntm_rn_pj_code FOREIGN KEY (runningteam_running_project_code) REFERENCES runningteams (running_project_code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE reports ADD CONSTRAINT fk_rp_rntm_rn_tc_code FOREIGN KEY (runningteam_running_teacher_code) REFERENCES runningteams (running_teacher_code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE reports ADD CONSTRAINT fk_rp_rntm_tm_code FOREIGN KEY (runningteam_team_code) REFERENCES runningteams (team_code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE reports ADD CONSTRAINT fk_rp_rntm_al_code FOREIGN KEY (runningteam_academiclevel_code) REFERENCES runningteams (academiclevel_code) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE leadevaluations ADD CONSTRAINT fk_le_rp_code FOREIGN KEY (report_code) REFERENCES reports (code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE leadevaluations ADD CONSTRAINT fk_le_st_code FOREIGN KEY (student_code) REFERENCES students (code) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE individualevaluations ADD CONSTRAINT fk_ie_rp_code FOREIGN KEY (report_code) REFERENCES reports (code) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE individualevaluations ADD CONSTRAINT fk_ie_st_code FOREIGN KEY (student_code) REFERENCES students (code) ON DELETE CASCADE ON UPDATE CASCADE;

COMMIT;