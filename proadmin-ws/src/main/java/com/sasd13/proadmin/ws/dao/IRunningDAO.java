package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

public interface IRunningDAO extends IReader<IRunning> {

	String TABLE = "runnings";
	String COLUMN_YEAR = "_year";
	String COLUMN_PROJECT = "_project";
	String COLUMN_TEACHER = "_teacher";

	long create(IRunning iRunning);

	void update(RunningUpdate updateWrapper);

	void delete(IRunning iRunning);
}
