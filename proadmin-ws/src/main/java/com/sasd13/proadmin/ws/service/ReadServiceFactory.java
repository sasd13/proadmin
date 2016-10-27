package com.sasd13.proadmin.ws.service;

import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws.service.member.TeacherReadService;

public class ReadServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> IReadService<T> make(Class<T> mClass) throws WSException {
		if (Teacher.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new TeacherReadService();
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return null;
		} else {
			throw new WSException("ReadService not found for class : " + mClass.getName());
		}
	}
}
