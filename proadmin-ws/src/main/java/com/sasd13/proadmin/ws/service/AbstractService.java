package com.sasd13.proadmin.ws.service;

import com.sasd13.javaex.service.IDeepReadService;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public abstract class AbstractService<T> implements IManageService<T>, IReadService<T>, IDeepReadService<T> {

	protected DAO dao;

	protected AbstractService() {
		dao = DAOManager.create();
	}
}
