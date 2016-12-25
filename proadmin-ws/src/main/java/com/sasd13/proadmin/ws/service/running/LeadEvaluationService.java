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
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.ILeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class LeadEvaluationService extends AbstractService<LeadEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationService.class);

	public LeadEvaluationService() {
		super();
	}

	@Override
	public void create(List<LeadEvaluation> leadEvaluations) {
		LOGGER.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(List<IUpdateWrapper<LeadEvaluation>> updateWrappers) {
		try {
			dao.open();

			ISession<LeadEvaluation> session = dao.getSession(LeadEvaluation.class);
			ILeadEvaluationUpdateWrapper leadEvaluationUpdateWrapper;

			for (IUpdateWrapper<LeadEvaluation> updateWrapper : updateWrappers) {
				leadEvaluationUpdateWrapper = (ILeadEvaluationUpdateWrapper) updateWrapper;

				LOGGER.info("update : reportNumber=" + leadEvaluationUpdateWrapper.getReportNumber() + ", studentNumber=" + leadEvaluationUpdateWrapper.getStudentNumber());
				session.update(leadEvaluationUpdateWrapper);
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
	public void delete(List<LeadEvaluation> leadEvaluations) {
		LOGGER.info("delete unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			dao.open();

			leadEvaluations = dao.getSession(LeadEvaluation.class).select(parameters);
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

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> readAll() {
		LOGGER.info("readAll");

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			dao.open();

			leadEvaluations = dao.getSession(LeadEvaluation.class).selectAll();
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

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			dao.open();

			leadEvaluations = dao.getDeepReader(LeadEvaluation.class).select(parameters);
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

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			dao.open();

			leadEvaluations = dao.getDeepReader(LeadEvaluation.class).selectAll();
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

		return leadEvaluations;
	}
}
