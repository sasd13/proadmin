package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class IndividualEvaluationReadService implements IReadService<IndividualEvaluation> {

	private static final Logger LOG = Logger.getLogger(IndividualEvaluationReadService.class);

	private DAO dao;

	public IndividualEvaluationReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) throws WSException {
		LOG.info("IndividualEvaluationReadService --> read : parameters=" + URLQueryUtils.toString(parameters));

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			dao.open();

			individualEvaluations = dao.getEntityDAO(IndividualEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("IndividualEvaluationReadService --> read failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> readAll() throws WSException {
		LOG.info("IndividualEvaluationReadService --> readAll");

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			dao.open();

			individualEvaluations = dao.getEntityDAO(IndividualEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOG.error("IndividualEvaluationReadService --> readAll failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> deepRead(Map<String, String[]> parameters) throws WSException {
		LOG.info("IndividualEvaluationReadService --> deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			dao.open();

			individualEvaluations = dao.getEntityDAO(IndividualEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("IndividualEvaluationReadService --> deepRead failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> deepReadAll() throws WSException {
		LOG.info("IndividualEvaluationReadService --> deepReadAll");

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		try {
			dao.open();

			individualEvaluations = dao.getDeepReader(IndividualEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOG.error("IndividualEvaluationReadService --> deepReadAll failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return individualEvaluations;
	}
}
