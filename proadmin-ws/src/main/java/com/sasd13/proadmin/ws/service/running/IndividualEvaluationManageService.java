package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class IndividualEvaluationManageService implements IManageService<IndividualEvaluation> {

	private static final Logger LOG = Logger.getLogger(IndividualEvaluationManageService.class);

	private DAO dao;

	public IndividualEvaluationManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(IndividualEvaluation individualEvaluation) throws WSException {
		throw new WSException("IndividualEvaluationManageService --> create unavailable");
	}

	@Override
	public void update(IndividualEvaluation individualEvaluation) throws WSException {
		LOG.info("IndividualEvaluationManageService --> update : studentNumber=" + individualEvaluation.getStudent().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(IndividualEvaluation.class).update(individualEvaluation);
		} catch (DAOException e) {
			LOG.error("IndividualEvaluationManageService --> update failed", e);
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
	public void delete(IndividualEvaluation individualEvaluation) throws WSException {
		throw new WSException("IndividualEvaluationManageService --> delete unavailable");
	}
}
