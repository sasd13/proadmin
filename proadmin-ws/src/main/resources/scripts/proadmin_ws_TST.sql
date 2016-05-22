INSERT INTO teachers (number, firstname, lastname, email) VALUES ('21010013', 'Samir', 'Said Ali', 'samir@email.com');
INSERT INTO passwords (password, teacher_id) VALUES ('2d48a13e67518ded1eb199cf67f8c3c2', 1);
INSERT INTO projects (academiclevel, code, title, description) VALUES ('L1', 'L1A', 'Projet A', 'Description du projet A');
INSERT INTO students (number, firstname, lastname, email, academiclevel) VALUES ('2130876', 'Samir', 'Said Ali', 'samir@email.com', 'L1');
INSERT INTO teams (code) VALUES ('L1A1');
INSERT INTO studentteams (student_id, team_id) VALUES (1, 1);
INSERT INTO runnings (year, teacher_id, project_id) VALUES (2016, 1, 1);
INSERT INTO runningteams (running_id, team_id) VALUES (1, 1);
INSERT INTO reports (meetingdate, sessionnumber, comment, runningteam_id) VALUES ('2016-01-01 00:00:00', 1, '', 1);
INSERT INTO leadevaluations (planningmark, planningcomment, communicationmark, communicationcomment, student_id, report_id) VALUES (0, '', 0, '', 1, 1);
INSERT INTO individualevaluations (mark, student_id, report_id) VALUES (0, 1, 1);

COMMIT;