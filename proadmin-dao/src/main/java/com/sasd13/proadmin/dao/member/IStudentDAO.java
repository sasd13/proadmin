package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.Student;

public interface IStudentDAO extends IEntityDAO<Student> {

	String TABLE = "students";
	String COLUMN_CODE = "code";
	String COLUMN_FIRSTNAME = "firstname";
	String COLUMN_LASTNAME = "lastname";
	String COLUMN_EMAIL = "email";
}
