package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.update.RunningUpdate;

public interface IRunningDAO extends IReader<Running> {

	String TABLE = "runnings";
	String COLUMN_YEAR = "_year";
	String COLUMN_PROJECT = "_project";
	String COLUMN_TEACHER = "_teacher";

	long create(Running running);

	void update(RunningUpdate runningUpdate);

	void delete(Running running);
}
