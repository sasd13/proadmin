package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.member.Student;

public interface IStudentDAO extends ISession<Student> {

	String TABLE = "students";
	String COLUMN_CODE = "_code";
	String COLUMN_FIRSTNAME = "_firstname";
	String COLUMN_LASTNAME = "_lastname";
	String COLUMN_EMAIL = "_email";
}
