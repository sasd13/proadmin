package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.running.ILeadEvaluationUpdateWrapper;

public class LeadEvaluationService extends Service<LeadEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationService.class);

	public LeadEvaluationService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(LeadEvaluation leadEvaluation) {
		LOGGER.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(IUpdateWrapper<LeadEvaluation> updateWrapper) {
		LOGGER.info("update : reportNumber=" + ((ILeadEvaluationUpdateWrapper) updateWrapper).getReportNumber() + ", studentNumber=" + ((ILeadEvaluationUpdateWrapper) updateWrapper).getStudentNumber());

		try {
			currentConnection().getSession(LeadEvaluation.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		LOGGER.info("delete unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			leadEvaluations = currentConnection().getSession(LeadEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> readAll() {
		LOGGER.info("readAll");

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			leadEvaluations = currentConnection().getSession(LeadEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			leadEvaluations = currentConnection().getDeepReader(LeadEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			leadEvaluations = currentConnection().getDeepReader(LeadEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return leadEvaluations;
	}
}
