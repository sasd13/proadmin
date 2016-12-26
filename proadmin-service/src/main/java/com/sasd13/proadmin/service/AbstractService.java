package com.sasd13.proadmin.service;

import com.sasd13.javaex.service.IDeepReadService;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.dao.DAO;

public abstract class AbstractService<T> implements IManageService<T>, IReadService<T>, IDeepReadService<T> {

	private DAO dao;

	protected AbstractService(DAO dao) {
		this.dao = dao;
	}

	protected DAO currentConnection() {
		return dao;
	}
}
