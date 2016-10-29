package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class ReportReadService implements IReadService<Report> {

	private static final Logger LOG = Logger.getLogger(ReportReadService.class);

	private DAO dao;

	public ReportReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Report> read(Map<String, String[]> parameters) throws WSException {
		LOG.info("ReportReadService --> read : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getEntityDAO(Report.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("ReportReadService --> read failed", e);
			throw new WSException(e.getMessage());
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
	public List<Report> readAll() throws WSException {
		LOG.info("ReportReadService --> readAll");

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getEntityDAO(Report.class).selectAll();
		} catch (DAOException e) {
			LOG.error("ReportReadService --> readAll failed", e);
			throw new WSException(e.getMessage());
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
	public List<Report> deepRead(Map<String, String[]> parameters) throws WSException {
		LOG.info("ReportReadService --> deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getEntityDAO(Report.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("ReportReadService --> deepRead failed", e);
			throw new WSException(e.getMessage());
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
	public List<Report> deepReadAll() throws WSException {
		LOG.info("ReportReadService --> deepReadAll");

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getDeepReader(Report.class).selectAll();
		} catch (DAOException e) {
			LOG.error("ReportReadService --> deepReadAll failed", e);
			throw new WSException(e.getMessage());
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
