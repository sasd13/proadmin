package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class ReportManageService implements IManageService<Report> {

	private static final Logger LOG = Logger.getLogger(ReportManageService.class);

	private DAO dao;

	public ReportManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Report report) throws WSException {
		LOG.info("ReportManageService --> create : number=" + report.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Report.class).insert(report);
		} catch (DAOException e) {
			LOG.error("ReportManageService --> create failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(Report report) throws WSException {
		LOG.info("ReportManageService --> create : number=" + report.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Report.class).update(report);
		} catch (DAOException e) {
			LOG.error("ReportManageService --> update failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void delete(Report report) throws WSException {
		LOG.info("ReportManageService --> create : number=" + report.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Report.class).update(report);
		} catch (DAOException e) {
			LOG.error("ReportManageService --> delete failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}
}
