package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class LeadEvaluationManageService implements IManageService<LeadEvaluation> {

	private static final Logger LOG = Logger.getLogger(LeadEvaluationManageService.class);

	private DAO dao;

	public LeadEvaluationManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(LeadEvaluation leadEvaluation) throws ServiceException {
		LOG.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(LeadEvaluation leadEvaluation) throws ServiceException {
		LOG.info("update : studentNumber=" + leadEvaluation.getStudent().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(LeadEvaluation.class).update(leadEvaluation);
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
	public void delete(LeadEvaluation leadEvaluation) throws ServiceException {
		LOG.info("delete unavailable");
		throw new ServiceException("Delete unavailable");
	}
}
