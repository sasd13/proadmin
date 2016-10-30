package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class LeadEvaluationManageService implements IManageService<LeadEvaluation> {

	private static final Logger LOG = Logger.getLogger(LeadEvaluationManageService.class);

	private DAO dao;

	public LeadEvaluationManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(LeadEvaluation leadEvaluation) throws WSException {
		LOG.info("create unavailable");
		throw new WSException("Service unavailable");
	}

	@Override
	public void update(LeadEvaluation leadEvaluation) throws WSException {
		LOG.info("update : studentNumber=" + leadEvaluation.getStudent().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(LeadEvaluation.class).update(leadEvaluation);
		} catch (DAOException e) {
			LOG.error("update failed");
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
	public void delete(LeadEvaluation leadEvaluation) throws WSException {
		LOG.info("delete unavailable");
		throw new WSException("Delete unavailable");
	}
}
