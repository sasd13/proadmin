package com.sasd13.proadmin.service;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.service.IDeepReadService;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.dao.DAO;

public abstract class Service<T> implements IManageService<T>, IReadService<T>, IDeepReadService<T> {

	private DAO dao;

	protected Service(DAO dao) {
		this.dao = dao;
	}

	protected ISession<T> getSession(Class<T> mClass) {
		return dao.getSession(mClass);
	}

	protected DeepReader<T> getDeepReader(Class<T> mClass) {
		return dao.getDeepReader(mClass);
	}
}
