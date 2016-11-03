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
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class ReportReadService implements IReadService<Report> {

	private static final Logger LOG = Logger.getLogger(ReportReadService.class);

	private DAO dao;

	public ReportReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Report> read(Map<String, String[]> parameters) throws ServiceException {
		LOG.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getSession(Report.class).select(parameters);
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return reports;
	}

	@Override
	public List<Report> readAll() throws ServiceException {
		LOG.info("readAll");

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getSession(Report.class).selectAll();
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return reports;
	}

	@Override
	public List<Report> deepRead(Map<String, String[]> parameters) throws ServiceException {
		LOG.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getDeepReader(Report.class).select(parameters);
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return reports;
	}

	@Override
	public List<Report> deepReadAll() throws ServiceException {
		LOG.info("deepReadAll");

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getDeepReader(Report.class).selectAll();
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return reports;
	}
}
