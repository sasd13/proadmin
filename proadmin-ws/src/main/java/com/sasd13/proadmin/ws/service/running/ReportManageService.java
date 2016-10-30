package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
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
	public void create(Report report) throws ServiceException {
		LOG.info("create : number=" + report.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Report.class).insert(report);
		} catch (DAOException e) {
			LOG.error("create failed");
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
	public void update(Report report) throws ServiceException {
		LOG.info("update : number=" + report.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Report.class).update(report);
		} catch (DAOException e) {
			LOG.error("update failed");
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
	public void delete(Report report) throws ServiceException {
		LOG.info("delete : number=" + report.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Report.class).delete(report);
		} catch (DAOException e) {
			LOG.error("delete failed");
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
