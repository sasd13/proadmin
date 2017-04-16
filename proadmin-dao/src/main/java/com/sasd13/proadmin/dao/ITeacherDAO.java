package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

public interface ITeacherDAO extends IReader<Teacher> {

	String TABLE = "teachers";
	String COLUMN_CODE = "_code";
	String COLUMN_USERID = "_userid";
	String COLUMN_FIRSTNAME = "_firstname";
	String COLUMN_LASTNAME = "_lastname";
	String COLUMN_EMAIL = "_email";

	long create(Teacher teacher);

	void update(TeacherUpdateWrapper updateWrapper);

	void delete(Teacher teacher);
}
