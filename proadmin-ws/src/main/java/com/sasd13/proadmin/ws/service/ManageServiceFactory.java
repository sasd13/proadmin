package com.sasd13.proadmin.ws.service;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws.service.member.TeacherManageService;

public class ManageServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> IManageService<T> make(Class<T> mClass) {
		if (Teacher.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new TeacherManageService();
		} else if () {
			
		} else {
			return null;
		}
	}
}
