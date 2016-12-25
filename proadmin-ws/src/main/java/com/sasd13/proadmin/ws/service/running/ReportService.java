package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class ReportService extends AbstractService<Report> {

	private static final Logger LOGGER = Logger.getLogger(ReportService.class);

	public ReportService() {
		super();
	}

	@Override
	public void create(List<Report> reports) {
		try {
			dao.open();

			ISession<Report> session = dao.getSession(Report.class);

			for (Report report : reports) {
				LOGGER.info("create : number=" + report.getNumber());
				session.insert(report);
			}
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
	}

	@Override
	public void update(List<IUpdateWrapper<Report>> updateWrappers) {
		try {
			dao.open();

			ISession<Report> session = dao.getSession(Report.class);
			IReportUpdateWrapper reportUpdateWrapper;

			for (IUpdateWrapper<Report> updateWrapper : updateWrappers) {
				reportUpdateWrapper = (IReportUpdateWrapper) updateWrapper;

				LOGGER.info("update : number=" + reportUpdateWrapper.getNumber());
				session.update(reportUpdateWrapper);
			}
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
	}

	@Override
	public void delete(List<Report> reports) {
		try {
			dao.open();

			ISession<Report> session = dao.getSession(Report.class);

			for (Report report : reports) {
				LOGGER.info("delete : number=" + report.getNumber());
				session.delete(report);
			}
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
	}

	@Override
	public List<Report> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getSession(Report.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}

		return reports;
	}

	@Override
	public List<Report> readAll() {
		LOGGER.info("readAll");

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getSession(Report.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}

		return reports;
	}

	@Override
	public List<Report> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getDeepReader(Report.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}

		return reports;
	}

	@Override
	public List<Report> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<Report> reports = new ArrayList<>();

		try {
			dao.open();

			reports = dao.getDeepReader(Report.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}

		return reports;
	}
}
