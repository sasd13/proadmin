package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

public interface IRunningDAO extends IReader<Running> {

	String TABLE = "runnings";
	String COLUMN_YEAR = "_year";
	String COLUMN_PROJECT = "_project";
	String COLUMN_TEACHER = "_teacher";

	long create(Running running);

	void update(RunningUpdateWrapper updateWrapper);

	void delete(Running running);
}
