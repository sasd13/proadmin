package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IEntityDAO;
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
	public void create(LeadEvaluation[] leadEvaluations) throws ServiceException {
		LOG.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(LeadEvaluation[] leadEvaluations) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<LeadEvaluation> leadEvaluationDAO = dao.getEntityDAO(LeadEvaluation.class);

			for (LeadEvaluation leadEvaluation : leadEvaluations) {
				LOG.info("update : studentNumber=" + leadEvaluation.getStudent().getNumber());
				leadEvaluationDAO.update(leadEvaluation);
			}
		} catch (DAOException e) {
			LOG.error("update failed. " + e.getMessage());
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
	public void delete(LeadEvaluation[] leadEvaluations) throws ServiceException {
		LOG.info("delete unavailable");
		throw new ServiceException("Service unavailable");
	}
}
