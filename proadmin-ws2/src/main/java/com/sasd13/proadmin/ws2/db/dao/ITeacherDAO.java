package com.sasd13.proadmin.ws2.db.dao;

import com.sasd13.javaex.dao.hibernate.ISession;
import com.sasd13.proadmin.bean.member.Teacher;

public interface ITeacherDAO extends ISession<Teacher> {

	String TABLE = "teachers";
	String COLUMN_CODE = "_code";
	String COLUMN_FIRSTNAME = "_firstname";
	String COLUMN_LASTNAME = "_lastname";
	String COLUMN_EMAIL = "_email";
}
