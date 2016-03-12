package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.Teacher;

public interface TeacherDAO extends IEntityDAO<Teacher> {
	
	String TABLE = "teachers";
	
	String COLUMN_ID = "teacher_id";
	String COLUMN_NUMBER = "teacher_number";
	String COLUMN_FIRSTNAME = "teacher_firstname";
	String COLUMN_LASTNAME = "teacher_lastname";
	String COLUMN_EMAIL = "teacher_email";
	String COLUMN_PASSWORD = "teacher_password";
}
