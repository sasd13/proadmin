package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IIndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class IndividualEvaluationService extends AbstractService<IndividualEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(IndividualEvaluationService.class);

	public IndividualEvaluationService() {
		super();
	}

	@Override
	public void create(List<IndividualEvaluation> individualEvaluations) {
		LOGGER.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(List<IUpdateWrapper<IndividualEvaluation>> updateWrappers) {
		try {
			dao.open();

			ISession<IndividualEvaluation> session = dao.getSession(IndividualEvaluation.class);
			IIndividualEvaluationUpdateWrapper individualEvaluationUpdateWrapper;

			for (IUpdateWrapper<IndividualEvaluation> updateWrapper : updateWrappers) {
				individualEvaluationUpdateWrapper = (IIndividualEvaluationUpdateWrapper) updateWrapper;

				LOGGER.info("update : reportNumber=" + individualEvaluationUpdateWrapper.getReportNumber() + ", studentNumber=" + individualEvaluationUpdateWrapper.getStudentNumber());
				session.update(individualEvaluationUpdateWrapper);
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
	public void delete(List<IndividualEvaluation> individualEvaluations) {
		LOGGER.info("delete unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			dao.open();

			individualEvaluations = dao.getSession(IndividualEvaluation.class).select(parameters);
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

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> readAll() {
		LOGGER.info("readAll");

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			dao.open();

			individualEvaluations = dao.getSession(IndividualEvaluation.class).selectAll();
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

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			dao.open();

			individualEvaluations = dao.getDeepReader(IndividualEvaluation.class).select(parameters);
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

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			dao.open();

			individualEvaluations = dao.getDeepReader(IndividualEvaluation.class).selectAll();
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

		return individualEvaluations;
	}
}
