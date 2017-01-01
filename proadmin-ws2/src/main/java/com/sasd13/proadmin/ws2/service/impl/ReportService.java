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
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IReportDAO;
import com.sasd13.proadmin.ws2.service.IService;

public class ReportService implements IService<Report> {

	private static final Logger LOGGER = Logger.getLogger(ReportService.class);

	@Autowired
	private IReportDAO dao;

	@Override
	public void create(Report report) {
		LOGGER.info("create : number=" + report.getNumber());

		try {
			dao.insert(report);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Report> updateWrapper) {
		LOGGER.info("update : number=" + ((IReportUpdateWrapper) updateWrapper).getNumber());

		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Report report) {
		LOGGER.info("delete : number=" + report.getNumber());

		try {
			dao.delete(report);
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
			reports = dao.select(parameters);
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
			reports = dao.selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return reports;
	}
}
