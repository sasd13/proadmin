package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class ReportManageService implements IManageService<Report> {

	private static final Logger LOG = Logger.getLogger(ReportManageService.class);

	private DAO dao;

	public ReportManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Report> reports) throws ServiceException {
		try {
			dao.open();

			ISession<Report> reportDAO = dao.getSession(Report.class);

			for (Report report : reports) {
				LOG.info("create : number=" + report.getNumber());
				reportDAO.insert(report);
			}
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
	}

	@Override
	public void update(List<Report> reports) throws ServiceException {
		try {
			dao.open();

			ISession<Report> reportDAO = dao.getSession(Report.class);

			for (Report report : reports) {
				LOG.info("update : number=" + report.getNumber());
				reportDAO.update(report);
			}
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
	}

	@Override
	public void delete(List<Report> reports) throws ServiceException {
		try {
			dao.open();

			ISession<Report> reportDAO = dao.getSession(Report.class);

			for (Report report : reports) {
				LOG.info("delete : number=" + report.getNumber());
				reportDAO.delete(report);
			}
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
	}
}
