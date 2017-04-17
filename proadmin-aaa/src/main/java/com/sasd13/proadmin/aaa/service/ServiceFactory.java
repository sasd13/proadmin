package com.sasd13.proadmin.aaa.service;

import com.sasd13.proadmin.aaa.dao.DAO;
import com.sasd13.proadmin.aaa.service.impl.UserService;

public class ServiceFactory {

	public static Object make(Class<?> mClass, DAO dao) {
		if (IUserService.class.isAssignableFrom(mClass)) {
			return new UserService(dao);
		} else {
			return null;
		}
	}
}
