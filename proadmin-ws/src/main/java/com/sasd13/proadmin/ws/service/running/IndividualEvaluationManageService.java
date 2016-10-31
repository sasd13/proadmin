package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class IndividualEvaluationManageService implements IManageService<IndividualEvaluation> {

	private static final Logger LOG = Logger.getLogger(IndividualEvaluationManageService.class);

	private DAO dao;

	public IndividualEvaluationManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(IndividualEvaluation[] individualEvaluations) throws ServiceException {
		LOG.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(IndividualEvaluation[] individualEvaluations) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<IndividualEvaluation> individualEvaluationDAO = dao.getEntityDAO(IndividualEvaluation.class);

			for (IndividualEvaluation individualEvaluation : individualEvaluations) {
				LOG.info("update : reportNumber=" + individualEvaluation.getReport().getNumber() + ", studentNumber=" + individualEvaluation.getStudent().getNumber());
				individualEvaluationDAO.update(individualEvaluation);
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
	public void delete(IndividualEvaluation[] individualEvaluations) throws ServiceException {
		LOG.info("delete unavailable");
		throw new ServiceException("Service unavailable");
	}
}
