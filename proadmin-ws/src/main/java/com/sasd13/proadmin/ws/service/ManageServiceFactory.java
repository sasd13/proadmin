package com.sasd13.proadmin.ws.service;

import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws.service.member.TeacherManageService;

public class ManageServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> IManageService<T> make(Class<T> mClass) throws WSException {
		if (Teacher.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new TeacherManageService();
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return null;
		} else {
			throw new WSException("ManageService not found for class : " + mClass.getName());
		}
	}
}
