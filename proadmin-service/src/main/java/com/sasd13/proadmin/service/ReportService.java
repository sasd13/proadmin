package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.DAO;

public class ReportService extends Service<Report> {

	private static final Logger LOGGER = Logger.getLogger(ReportService.class);

	public ReportService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Report report) {
		try {
			getSession(Report.class).insert(report);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Report> updateWrapper) {
		try {
			getSession(Report.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Report report) {
		try {
			getSession(Report.class).delete(report);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Report> read(Map<String, String[]> parameters) {
		List<Report> reports = new ArrayList<>();

		try {
			reports = getSession(Report.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return reports;
	}

	@Override
	public List<Report> readAll() {
		List<Report> reports = new ArrayList<>();

		try {
			reports = getSession(Report.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return reports;
	}

	@Override
	public List<Report> deepRead(Map<String, String[]> parameters) {
		List<Report> reports = new ArrayList<>();

		try {
			reports = getDeepReader(Report.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return reports;
	}

	@Override
	public List<Report> deepReadAll() {
		List<Report> reports = new ArrayList<>();

		try {
			reports = getDeepReader(Report.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return reports;
	}
}
