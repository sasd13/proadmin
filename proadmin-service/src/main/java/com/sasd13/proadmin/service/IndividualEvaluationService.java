package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.DAO;

public class IndividualEvaluationService extends Service<IndividualEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(IndividualEvaluationService.class);

	public IndividualEvaluationService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(IndividualEvaluation individualEvaluation) {
		try {
			getSession(IndividualEvaluation.class).insert(individualEvaluation);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<IndividualEvaluation> updateWrapper) {
		try {
			getSession(IndividualEvaluation.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) {
		try {
			getSession(IndividualEvaluation.class).delete(individualEvaluation);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) {
		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			individualEvaluations = getSession(IndividualEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> readAll() {
		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			individualEvaluations = getSession(IndividualEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> deepRead(Map<String, String[]> parameters) {
		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			individualEvaluations = getDeepReader(IndividualEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> deepReadAll() {
		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			individualEvaluations = getDeepReader(IndividualEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return individualEvaluations;
	}
}
