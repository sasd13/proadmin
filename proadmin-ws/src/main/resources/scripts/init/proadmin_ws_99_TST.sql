USE proadmin_ws;

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

INSERT INTO projects(code,datecreation,title,description) VALUES ('2015PR01','2015-01-01 00:00:00','Projet A','Description du projet A');
INSERT INTO projects(code,datecreation,title,description) VALUES ('2016PR02','2016-01-01 00:00:00','Projet B','Description du projet B');
INSERT INTO projects(code,datecreation,title,description) VALUES ('2016PR03','2016-01-01 00:00:00','Projet C','Description du projet C');

-- TEACHERS

INSERT INTO teachers(code,firstname,lastname,email) VALUES ('21010013','Samir','Said Ali','samir@email.com');
INSERT INTO teachers(code,firstname,lastname,email) VALUES ('21010014','Nasri','Samir','nasri@email.com');

-- STUDENTS

INSERT INTO students(code,firstname,lastname,email) VALUES ('2130876','Karim','Benzema','benzema@email.com');
INSERT INTO students(code,firstname,lastname,email) VALUES ('2130878','Gareth','Bale','bale@email.com');
INSERT INTO students(code,firstname,lastname,email) VALUES ('2130877','Cristiano','Ronaldo','ronaldo@email.com');

-- TEAMS

INSERT INTO teams(code) VALUES ('2015TE01');
INSERT INTO teams(code) VALUES ('2016TE02');

-- STUDENTTEAMS

INSERT INTO studentteams(student_code,team_code) VALUES ('2130876','2015TE01');
INSERT INTO studentteams(student_code,team_code) VALUES ('2130877','2015TE01');
INSERT INTO studentteams(student_code,team_code) VALUES ('2130878','2016TE02');

-- RUNNINGS

INSERT INTO runnings(year,project_code,teacher_code) VALUES (2015,'2015PR01','21010013');
INSERT INTO runnings(year,project_code,teacher_code) VALUES (2016,'2016PR02','21010014');

-- ACADEMICLEVEL

INSERT INTO academiclevels(code) VALUES ('L1');
INSERT INTO academiclevels(code) VALUES ('L2');
INSERT INTO academiclevels(code) VALUES ('L3');
INSERT INTO academiclevels(code) VALUES ('M1');
INSERT INTO academiclevels(code) VALUES ('M2');

-- RUNNINGTEAMS

INSERT INTO runningteams(running_year,running_project_code,running_teacher_code,team_code,academiclevel_code) VALUES (2015,'2015PR01','21010013','2015TE01','L1');
INSERT INTO runningteams(running_year,running_project_code,running_teacher_code,team_code,academiclevel_code) VALUES (2016,'2016PR02','21010014','2016TE02','L2');

-- REPORTS

INSERT INTO reports(code,datemeeting,session,comment,runningteam_running_year,runningteam_running_project_code,runningteam_running_teacher_code,runningteam_team_code,runningteam_academiclevel_code) VALUES ('2015RE0001','2015-01-01 00:00:00',1,null,2015,'2015PR01','21010013','2015TE01','L1');

-- LEADEVALUATIONS

INSERT INTO leadevaluations(planningmark,planningcomment,communicationmark,communicationcomment,report_code,student_code) VALUES (0,null,0,null,'2015RE0001','2130876');

-- INDIVIDUALEVALUATIONS

INSERT INTO individualevaluations(mark,report_code,student_code) VALUES (0,'2015RE0001','2130876');

COMMIT;