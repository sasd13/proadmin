package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.Student;

public interface StudentDAO extends IEntityDAO<Student> {

	String TABLE = "students";

	String COLUMN_ID = "id";
	String COLUMN_NUMBER = "number";
	String COLUMN_FIRSTNAME = "firstname";
	String COLUMN_LASTNAME = "lastname";
	String COLUMN_EMAIL = "email";
	String COLUMN_ACADEMICLEVEL = "academiclevel";
}
