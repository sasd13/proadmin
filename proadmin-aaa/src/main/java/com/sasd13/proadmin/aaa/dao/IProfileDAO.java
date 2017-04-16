package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.IDAO;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.aaa.bean.Profile;

public interface IProfileDAO extends IDAO, ISession<Profile> {

	String TABLE = "profiles";
	String COLUMN_USERNAME = "_username";
	String COLUMN_PASSWORD = "_password";
	String COLUMN_CODE = "_code";
}
