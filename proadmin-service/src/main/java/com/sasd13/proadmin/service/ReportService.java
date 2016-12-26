package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;

public class ReportService extends Service<Report> {

	private static final Logger LOGGER = Logger.getLogger(ReportService.class);

	public ReportService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Report report) {
		LOGGER.info("create : number=" + report.getNumber());

		try {
			currentConnection().getSession(Report.class).insert(report);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Report> updateWrapper) {
		LOGGER.info("update : number=" + ((IReportUpdateWrapper) updateWrapper).getNumber());

		try {
			currentConnection().getSession(Report.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Report report) {
		LOGGER.info("delete : number=" + report.getNumber());

		try {
			currentConnection().getSession(Report.class).delete(report);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Report> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			reports = currentConnection().getSession(Report.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return reports;
	}

	@Override
	public List<Report> readAll() {
		LOGGER.info("readAll");

		List<Report> reports = new ArrayList<>();

		try {
			reports = currentConnection().getSession(Report.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return reports;
	}

	@Override
	public List<Report> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			reports = currentConnection().getDeepReader(Report.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return reports;
	}

	@Override
	public List<Report> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<Report> reports = new ArrayList<>();

		try {
			reports = currentConnection().getDeepReader(Report.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return reports;
	}
}
