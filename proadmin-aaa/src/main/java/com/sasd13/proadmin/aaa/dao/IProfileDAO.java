package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.profile.Profile;
import com.sasd13.proadmin.util.wrapper.profile.ProfileUpdateWrapper;

public interface IProfileDAO extends IReader<Profile> {

	String TABLE = "profiles";
	String COLUMN_USERID = "_userid";
	String COLUMN_ROLES = "_roles";
	String COLUMN_INTERMEDIARY = "_intermediary";
	String COLUMN_EMAIL = "_email";

	long create(Profile profile);

	void update(ProfileUpdateWrapper updateWrapper);
}
