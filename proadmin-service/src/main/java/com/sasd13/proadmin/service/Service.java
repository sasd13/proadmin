package com.sasd13.proadmin.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.service.IDeepReadService;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
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

	@Override
	public void create(T t) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public void update(IUpdateWrapper<T> updateWrapper) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public void delete(T t) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public List<T> read(Map<String, String[]> parameters) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public List<T> readAll() {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public List<T> deepRead(Map<String, String[]> parameters) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public List<T> deepReadAll() {
		throw new NotImplementedException("Not implemented");
	}
}
