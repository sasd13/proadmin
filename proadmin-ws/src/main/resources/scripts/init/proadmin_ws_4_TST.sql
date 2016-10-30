DELETE FROM individualevaluations;
DELETE FROM leadevaluations;
DELETE FROM reports;
DELETE FROM runningteams;
DELETE FROM academiclevels;
DELETE FROM runnings;
DELETE FROM studentteams;
DELETE FROM teams;
DELETE FROM students;
DELETE FROM teachers;
DELETE FROM projects;

-- PROJECTS

INSERT INTO projects(code,datecreation,title,description) VALUES ('2016PR01','2016-01-01 00:00:00','Projet A','Description du projet A');

-- TEACHERS

INSERT INTO teachers(code,firstname,lastname,email) VALUES ('21010013','Samir','Said Ali','samir@email.com');

-- STUDENTS

INSERT INTO students(code,firstname,lastname,email) VALUES ('2130876','Samir','Nasri','nasri@email.com');

-- TEAMS

INSERT INTO teams(code) VALUES ('2016TE01');

-- STUDENTTEAMS

INSERT INTO studentteams(student_code,team_code) VALUES ('2130876','2016TE01');

-- RUNNINGS

INSERT INTO runnings(year,project_code,teacher_code) VALUES (2016,'2016PR01','21010013');

-- ACADEMICLEVEL

INSERT INTO academiclevels(code) VALUES ('L1');
INSERT INTO academiclevels(code) VALUES ('L2');
INSERT INTO academiclevels(code) VALUES ('L3');
INSERT INTO academiclevels(code) VALUES ('M1');
INSERT INTO academiclevels(code) VALUES ('M2');

-- RUNNINGTEAMS

INSERT INTO runningteams(running_project_code,running_teacher_code,team_code,academiclevel_code) VALUES ('2016RU01','21010013','2016TE01','L1');

-- REPORTS

INSERT INTO reports(code,datemeeting,session,comment,runningteam_running_project_code,runningteam_running_teacher_code,runningteam_team_code,runningteam_academiclevel_code) VALUES ('2016RE0001','2016-01-01 00:00:00',1,null,'2016RU01','21010013','2016TE01','L1');

-- LEADEVALUATIONS

INSERT INTO leadevaluations(planningmark,planningcomment,communicationmark,communicationcomment,report_code,student_code) VALUES (0,null,0,null,'2016RE0001','2130876');

-- INDIVIDUALEVALUATIONS

INSERT INTO individualevaluations(mark,report_code,student_code) VALUES (0,'2016RE0001','2130876');

COMMIT;