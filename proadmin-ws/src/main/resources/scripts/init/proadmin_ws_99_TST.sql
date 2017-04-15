SET search_path TO proadmin_ws;

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

INSERT INTO projects(_datecreation,_title,_description) VALUES (TIMESTAMP '2015-01-01 00:00:00','Projet A','Description du projet A');
INSERT INTO projects(_datecreation,_title,_description) VALUES (TIMESTAMP '2016-01-01 00:00:00','Projet B','Description du projet B');
INSERT INTO projects(_datecreation,_title,_description) VALUES (TIMESTAMP '2016-01-01 00:00:00','Projet C','Description du projet C');

-- TEACHERS

INSERT INTO teachers(_firstname,_lastname,_email) VALUES ('Samir','Said Ali','samir@email.com');
INSERT INTO teachers(_firstname,_lastname,_email) VALUES ('Nasri','Samir','nasri@email.com');

-- STUDENTS

INSERT INTO students(_firstname,_lastname,_email) VALUES ('Karim','Benzema','benzema@email.com');
INSERT INTO students(_firstname,_lastname,_email) VALUES ('Cristiano','Ronaldo','ronaldo@email.com');
INSERT INTO students(_firstname,_lastname,_email) VALUES ('Gareth','Bale','bale@email.com');
INSERT INTO students(_firstname,_lastname,_email) VALUES ('Luka','Modric','modric@email.com');

-- TEAMS

INSERT INTO teams(_name) VALUES ('Team 1');
INSERT INTO teams(_name) VALUES ('Team 2');

-- STUDENTTEAMS

INSERT INTO studentteams(_student,_team) VALUES ('ST-1','TM-1');
INSERT INTO studentteams(_student,_team) VALUES ('ST-2','TM-1');
INSERT INTO studentteams(_student,_team) VALUES ('ST-3','TM-2');
INSERT INTO studentteams(_student,_team) VALUES ('ST-4','TM-2');

-- RUNNINGS

INSERT INTO runnings(_year,_project,_teacher) VALUES (2015,'2015PR01','21010013');
INSERT INTO runnings(_year,_project,_teacher) VALUES (2016,'2016PR02','21010014');

-- ACADEMICLEVEL

INSERT INTO academiclevels(_code) VALUES ('L1');
INSERT INTO academiclevels(_code) VALUES ('L2');
INSERT INTO academiclevels(_code) VALUES ('L3');
INSERT INTO academiclevels(_code) VALUES ('M1');
INSERT INTO academiclevels(_code) VALUES ('M2');

-- RUNNINGTEAMS

INSERT INTO runningteams(_year,_project,_teacher,_team,_academiclevel) VALUES (2015,'2015PR01','21010013','2015TE01','L1');
INSERT INTO runningteams(_year,_project,_teacher,_team,_academiclevel) VALUES (2016,'2016PR02','21010014','2016TE02','L2');

-- REPORTS

INSERT INTO reports(_datemeeting,_session,_comment,_year,_project,_teacher,_team,_academiclevel) VALUES (TIMESTAMP '2015-01-01 00:00:00',1,'comment',2015,'2015PR01','21010013','2015TE01','L1');

-- LEADEVALUATIONS

INSERT INTO leadevaluations(_planningmark,_planningcomment,_communicationmark,_communicationcomment,_report,_student) VALUES (9,null,10,null,'RE-1','2130876');

-- INDIVIDUALEVALUATIONS

INSERT INTO individualevaluations(_mark,_report,_student) VALUES (7,'RP-1','2130876');
INSERT INTO individualevaluations(_mark,_report,_student) VALUES (8,'RP-1','2130877');

--COMMIT;