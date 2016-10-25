package com.sasd13.proadmin.ws.service;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws.service.member.TeacherReadService;

public class ReadServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> IReadService<T> make(Class<T> mClass) {
		if (Teacher.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new TeacherReadService();
		} else if () {
			
		} else {
			return null;
		}
	}
}
