package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class RunningReadService implements IReadService<Running> {

	private static final Logger LOG = Logger.getLogger(RunningReadService.class);

	private DAO dao;

	public RunningReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) throws ServiceException {
		LOG.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Running> runnings = new ArrayList<>();

		try {
			dao.open();

			runnings = dao.getEntityDAO(Running.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("read failed. " + e.getMessage());
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return runnings;
	}

	@Override
	public List<Running> readAll() throws ServiceException {
		LOG.info("readAll");

		List<Running> runnings = new ArrayList<>();

		try {
			dao.open();

			runnings = dao.getEntityDAO(Running.class).selectAll();
		} catch (DAOException e) {
			LOG.error("readAll failed. " + e.getMessage());
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return runnings;
	}

	@Override
	public List<Running> deepRead(Map<String, String[]> parameters) throws ServiceException {
		LOG.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<Running> runnings = new ArrayList<>();

		try {
			dao.open();

			runnings = dao.getDeepReader(Running.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("deepRead failed. " + e.getMessage());
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return runnings;
	}

	@Override
	public List<Running> deepReadAll() throws ServiceException {
		LOG.info("deepReadAll");

		List<Running> runnings = new ArrayList<>();

		try {
			dao.open();

			runnings = dao.getDeepReader(Running.class).selectAll();
		} catch (DAOException e) {
			LOG.error("deepReadAll failed. " + e.getMessage());
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return runnings;
	}
}
