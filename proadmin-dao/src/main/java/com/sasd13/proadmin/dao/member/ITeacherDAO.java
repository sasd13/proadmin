package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.Teacher;

public interface ITeacherDAO extends IEntityDAO<Teacher> {

	String TABLE = "teachers";
	String COLUMN_ID = "id";
	String COLUMN_NUMBER = "number";
	String COLUMN_FIRSTNAME = "firstname";
	String COLUMN_LASTNAME = "lastname";
	String COLUMN_EMAIL = "email";
}
