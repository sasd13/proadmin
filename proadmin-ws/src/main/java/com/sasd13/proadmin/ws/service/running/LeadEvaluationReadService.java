package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class LeadEvaluationReadService implements IReadService<LeadEvaluation> {

	private static final Logger LOG = Logger.getLogger(LeadEvaluationReadService.class);

	private DAO dao;

	public LeadEvaluationReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) throws WSException {
		LOG.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			dao.open();

			leadEvaluations = dao.getEntityDAO(LeadEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("read failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> readAll() throws WSException {
		LOG.info("readAll");

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			dao.open();

			leadEvaluations = dao.getEntityDAO(LeadEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOG.error("readAll failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> deepRead(Map<String, String[]> parameters) throws WSException {
		LOG.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			dao.open();

			leadEvaluations = dao.getDeepReader(LeadEvaluation.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("deepRead failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> deepReadAll() throws WSException {
		LOG.info("deepReadAll");

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		try {
			dao.open();

			leadEvaluations = dao.getDeepReader(LeadEvaluation.class).selectAll();
		} catch (DAOException e) {
			LOG.error("deepReadAll failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return leadEvaluations;
	}
}
