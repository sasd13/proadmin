package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class ReportManageService implements IManageService<Report> {

	private static final Logger LOGGER = Logger.getLogger(ReportManageService.class);

	private DAO dao;

	public ReportManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Report> reports) throws ServiceException {
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
	public void update(List<IUpdateWrapper<Report>> updateWrappers) throws ServiceException {
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
	public void delete(List<Report> reports) throws ServiceException {
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
}
