package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IManager;
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
	public void create(List<LeadEvaluation> leadEvaluations) throws ServiceException {
		LOG.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(List<LeadEvaluation> leadEvaluations) throws ServiceException {
		try {
			dao.open();

			IManager<LeadEvaluation> leadEvaluationDAO = dao.getSession(LeadEvaluation.class);

			for (LeadEvaluation leadEvaluation : leadEvaluations) {
				LOG.info("update : studentNumber=" + leadEvaluation.getStudent().getNumber());
				leadEvaluationDAO.update(leadEvaluation);
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
	public void delete(List<LeadEvaluation> leadEvaluations) throws ServiceException {
		LOG.info("delete unavailable");
		throw new ServiceException("Service unavailable");
	}
}
