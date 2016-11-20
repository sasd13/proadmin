package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class LeadEvaluationReadService implements IReadService<LeadEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationReadService.class);

	private DAO dao;

	public LeadEvaluationReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) throws ServiceException {
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
	public List<LeadEvaluation> readAll() throws ServiceException {
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
	public List<LeadEvaluation> deepRead(Map<String, String[]> parameters) throws ServiceException {
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
	public List<LeadEvaluation> deepReadAll() throws ServiceException {
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
