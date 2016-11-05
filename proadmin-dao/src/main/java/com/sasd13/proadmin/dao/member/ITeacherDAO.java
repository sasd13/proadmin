package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.member.Teacher;

public interface ITeacherDAO extends ISession<Teacher> {

	String TABLE = "teachers";
	String COLUMN_CODE = "code";
	String COLUMN_FIRSTNAME = "firstname";
	String COLUMN_LASTNAME = "lastname";
	String COLUMN_EMAIL = "email";
}
