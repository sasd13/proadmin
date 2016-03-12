package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.Student;

public interface StudentDAO extends IEntityDAO<Student> {
	
	String TABLE = "students";
	
	String COLUMN_ID = "student_id";
	String COLUMN_NUMBER = "student_number";
	String COLUMN_ACADEMICLEVEL = "student_academiclevel";
	String COLUMN_FIRSTNAME = "student_firstname";
	String COLUMN_LASTNAME = "student_lastname";
	String COLUMN_EMAIL = "student_email";
}
