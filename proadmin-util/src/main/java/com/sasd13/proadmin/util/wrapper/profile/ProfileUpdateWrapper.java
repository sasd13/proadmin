package com.sasd13.proadmin.util.wrapper.profile;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.profile.Profile;

public class ProfileUpdateWrapper implements IUpdateWrapper<Profile> {

	private Profile profile;
	private String userID;

	@Override
	public Profile getWrapped() {
		return profile;
	}

	@Override
	public void setWrapped(Profile profile) {
		this.profile = profile;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}
