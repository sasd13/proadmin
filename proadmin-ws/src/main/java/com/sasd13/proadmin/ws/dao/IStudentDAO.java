package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.ws.bean.update.StudentUpdate;

public interface IStudentDAO extends IReader<Student> {

	String TABLE = "students";
	String COLUMN_CODE = "_code";
	String COLUMN_FIRSTNAME = "_firstname";
	String COLUMN_LASTNAME = "_lastname";
	String COLUMN_EMAIL = "_email";

	long create(Student student);

	void update(StudentUpdate updateWrapper);

	void delete(Student student);
}
