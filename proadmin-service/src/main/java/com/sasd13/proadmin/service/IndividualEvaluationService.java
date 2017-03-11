package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.running.IIndividualEvaluationUpdateWrapper;

public class IndividualEvaluationService extends Service<IndividualEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(IndividualEvaluationService.class);

	public IndividualEvaluationService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(IndividualEvaluation individualEvaluation) {
		LOGGER.info("create : report=" + individualEvaluation.getReport().getNumber() + ", student=" + individualEvaluation.getStudent().getNumber());

		try {
			getSession(IndividualEvaluation.class).insert(individualEvaluation);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<IndividualEvaluation> updateWrapper) {
		LOGGER.info("update : reportNumber=" + ((IIndividualEvaluationUpdateWrapper) updateWrapper).getReportNumber() + ", studentNumber=" + ((IIndividualEvaluationUpdateWrapper) updateWrapper).getStudentNumber());

		try {
			getSession(IndividualEvaluation.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) {
		LOGGER.info("delete : report=" + individualEvaluation.getReport().getNumber() + ", student=" + individualEvaluation.getStudent().getNumber());

		try {
			getSession(IndividualEvaluation.class).delete(individualEvaluation);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

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
		LOGGER.info("readAll");

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
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

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
		LOGGER.info("deepReadAll");

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
