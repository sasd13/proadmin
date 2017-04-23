SET search_path TO proadmin_db2;

DELETE FROM individualevaluations;
DELETE FROM leadevaluations;
DELETE FROM reports;
DELETE FROM runningteams;
DELETE FROM runnings;
DELETE FROM studentteams;
DELETE FROM teams;
DELETE FROM students;
DELETE FROM teachers;
DELETE FROM projects;

-- PROJECTS

INSERT INTO projects(_datecreation,_title,_description) VALUES (TIMESTAMP WITH TIME ZONE '2015-01-01 00:00:00+01','Projet A','Description du projet A');
INSERT INTO projects(_datecreation,_title,_description) VALUES (TIMESTAMP WITH TIME ZONE '2016-01-01 00:00:00+01','Projet B','Description du projet B');
INSERT INTO projects(_datecreation,_title,_description) VALUES (TIMESTAMP WITH TIME ZONE '2017-01-01 00:00:00+01','Projet C','Description du projet C');

-- TEACHERS

INSERT INTO teachers(_code,_firstname,_lastname,_email) VALUES ('1','Samir','Said Ali','samir@email.com');

-- STUDENTS

INSERT INTO students(_code,_firstname,_lastname,_email) VALUES ('21010013','Karim','Benzema','benzema@email.com');
INSERT INTO students(_code,_firstname,_lastname,_email) VALUES ('21010014','Cristiano','Ronaldo','ronaldo@email.com');
INSERT INTO students(_code,_firstname,_lastname,_email) VALUES ('21010015','Gareth','Bale','bale@email.com');
INSERT INTO students(_code,_firstname,_lastname,_email) VALUES ('21010016','Luka','Modric','modric@email.com');

-- TEAMS

INSERT INTO teams(_name) VALUES ('Team 1');
INSERT INTO teams(_name) VALUES ('Team 2');

-- STUDENTTEAMS

INSERT INTO studentteams(_student,_team)
	SELECT st._id, tm._id
	FROM students st, teams tm
	WHERE st._code = '21010013' AND tm._code = 'TM-1';

INSERT INTO studentteams(_student,_team)
	SELECT st._id, tm._id
	FROM students st, teams tm
	WHERE st._code = '21010014' AND tm._code = 'TM-1';

INSERT INTO studentteams(_student,_team)
	SELECT st._id, tm._id
	FROM students st, teams tm
	WHERE st._code = '21010015' AND tm._code = 'TM-2';

INSERT INTO studentteams(_student,_team)
	SELECT st._id, tm._id
	FROM students st, teams tm
	WHERE st._code = '21010016' AND tm._code = 'TM-2';

-- RUNNINGS

INSERT INTO runnings(_year,_project,_teacher)
	SELECT 2015, pr._id, tc._id
	FROM projects pr, teachers tc
	WHERE pr._code = 'PR-1' AND tc._code = '1';

INSERT INTO runnings(_year,_project,_teacher)
	SELECT 2016, pr._id, tc._id
	FROM projects pr, teachers tc
	WHERE pr._code = 'PR-2' AND tc._code = '1';

-- RUNNINGTEAMS

INSERT INTO runningteams(_running,_team,_academiclevel)
	SELECT rn._id, tm._id, al._id
	FROM
		runnings rn,
		teams tm,
		academiclevels al
	WHERE
		rn._year = 2015
		AND rn._project = (SELECT pr._id FROM projects pr WHERE pr._code = 'PR-1')
		AND rn._teacher = (SELECT tc._id FROM teachers tc WHERE tc._code = '1')
		AND tm._code = 'TM-1'
		AND al._code = 'L1';

INSERT INTO runningteams(_running,_team,_academiclevel)
	SELECT rn._id, tm._id, al._id
	FROM
		runnings rn,
		teams tm,
		academiclevels al
	WHERE
		rn._year = 2016
		AND rn._project = (SELECT pr._id FROM projects pr WHERE pr._code = 'PR-2')
		AND rn._teacher = (SELECT tc._id FROM teachers tc WHERE tc._code = '1')
		AND tm._code = 'TM-2'
		AND al._code = 'L2';

-- REPORTS

INSERT INTO reports(_datemeeting,_session,_comment,_runningteam)
	SELECT TIMESTAMP WITH TIME ZONE '2015-01-01 00:00:00+01', 1, null, rntm._id
	FROM
		runningteams rntm
		INNER JOIN runnings rn ON rntm._running = rn._id
		INNER JOIN teams tm ON rntm._team = tm._id
		INNER JOIN academiclevels al ON rntm._academiclevel = al._id
	WHERE
		rn._year = 2015
		AND rn._project = (SELECT pr._id FROM projects pr WHERE pr._code = 'PR-1')
		AND rn._teacher = (SELECT tc._id FROM teachers tc WHERE tc._code = '1')
		AND tm._code = 'TM-1'
		AND al._code = 'L1';

-- LEADEVALUATIONS

INSERT INTO leadevaluations(_planningmark,_planningcomment,_communicationmark,_communicationcomment,_report,_student)
	SELECT 9, null, 10, null, rp._id, st._id
	FROM reports rp, students st
	WHERE rp._code = 'RP-1' AND st._code = '21010013';

-- INDIVIDUALEVALUATIONS

INSERT INTO individualevaluations(_mark,_report,_student)
	SELECT 7, rp._id, st._id
	FROM reports rp, students st
	WHERE rp._code = 'RP-1' AND st._code = '21010013';

INSERT INTO individualevaluations(_mark,_report,_student)
	SELECT 8, rp._id, st._id
	FROM reports rp, students st
	WHERE rp._code = 'RP-1' AND st._code = '21010014';

--COMMIT;