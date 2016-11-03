package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.IManager;
import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.member.Student;

public interface IStudentDAO extends IManager<Student>, IReader<Student> {

	String TABLE = "students";
	String COLUMN_CODE = "code";
	String COLUMN_FIRSTNAME = "firstname";
	String COLUMN_LASTNAME = "lastname";
	String COLUMN_EMAIL = "email";
}
