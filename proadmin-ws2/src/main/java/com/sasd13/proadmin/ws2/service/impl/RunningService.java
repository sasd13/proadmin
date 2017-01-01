package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.IRunningDAO;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;
import com.sasd13.proadmin.ws2.service.IService;

public class RunningService implements IService<Running> {

	private static final Logger LOGGER = Logger.getLogger(RunningService.class);

	@Autowired
	private IRunningDAO dao;

	@Override
	public void create(Running running) {
		LOGGER.info("create : year=" + running.getYear() + ", projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		try {
			dao.insert(running);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Running> updateWrapper) {
		LOGGER.info("update : year=" + ((IRunningUpdateWrapper) updateWrapper).getYear() + ", projectCode=" + ((IRunningUpdateWrapper) updateWrapper).getProjectCode() + ", teacherNumber=" + ((IRunningUpdateWrapper) updateWrapper).getTeacherNumber());

		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Running running) {
		LOGGER.info("delete : year=" + running.getYear() + ", projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		try {
			dao.delete(running);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Running> runnings = new ArrayList<>();

		try {
			runnings = dao.select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}

	@Override
	public List<Running> readAll() {
		LOGGER.info("readAll");

		List<Running> runnings = new ArrayList<>();

		try {
			runnings = dao.selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}
}
