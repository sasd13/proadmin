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
		LOG.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getEntityDAO(Report.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("read failed");
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
		LOG.info("readAll");

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getEntityDAO(Report.class).selectAll();
		} catch (DAOException e) {
			LOG.error("readAll failed");
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
		LOG.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getDeepReader(Report.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("deepRead failed");
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
		LOG.info("deepReadAll");

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getDeepReader(Report.class).selectAll();
		} catch (DAOException e) {
			LOG.error("deepReadAll failed");
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