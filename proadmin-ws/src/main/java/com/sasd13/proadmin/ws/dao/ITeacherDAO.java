package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.ws.bean.Teacher;
import com.sasd13.proadmin.ws.bean.update.TeacherUpdate;

public interface ITeacherDAO extends IReader<Teacher> {

	String TABLE = "teachers";
	String COLUMN_CODE = "_code";
	String COLUMN_FIRSTNAME = "_firstname";
	String COLUMN_LASTNAME = "_lastname";
	String COLUMN_EMAIL = "_email";

	long create(Teacher teacher);

	void update(TeacherUpdate teacherUpdate);

	void delete(Teacher teacher);
}
