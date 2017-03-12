package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.DAO;

public class LeadEvaluationService extends Service<LeadEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationService.class);

	public LeadEvaluationService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(LeadEvaluation leadEvaluation) {
		try {
			getSession(LeadEvaluation.class).insert(leadEvaluation);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<LeadEvaluation> updateWrapper) {
		try {
			getSession(LeadEvaluation.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		try {
			getSession(LeadEvaluation.class).delete(leadEvaluation);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) {
		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			leadEvaluations = getSession(LeadEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> readAll() {
		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			leadEvaluations = getSession(LeadEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> deepRead(Map<String, String[]> parameters) {
		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			leadEvaluations = getDeepReader(LeadEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> deepReadAll() {
		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			leadEvaluations = getDeepReader(LeadEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return leadEvaluations;
	}
}
