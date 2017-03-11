package com.sasd13.proadmin.ws2.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.ILeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.ws2.db.dto.LeadEvaluationDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.LeadEvaluationDTOAdapter;
import com.sasd13.proadmin.ws2.service.IService;

public class LeadEvaluationService implements IService<LeadEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationService.class);

	@Autowired
	private ILeadEvaluationDAO dao;

	@Override
	public void create(LeadEvaluation leadEvaluation) {
		LOGGER.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(IUpdateWrapper<LeadEvaluation> updateWrapper) {
		LOGGER.info("update : reportNumber=" + ((ILeadEvaluationUpdateWrapper) updateWrapper).getReportNumber() + ", studentNumber=" + ((ILeadEvaluationUpdateWrapper) updateWrapper).getStudentNumber());

		try {
			dao.update(updateWrapper);
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
		LeadEvaluationDTOAdapter adapter = new LeadEvaluationDTOAdapter();

		try {
			List<Serializable> results = dao.select(parameters);

			for (Serializable result : results) {
				leadEvaluations.add(adapter.adapt((LeadEvaluationDTO) result));
			}
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
		LeadEvaluationDTOAdapter adapter = new LeadEvaluationDTOAdapter();

		try {
			List<Serializable> results = dao.selectAll();

			for (Serializable result : results) {
				leadEvaluations.add(adapter.adapt((LeadEvaluationDTO) result));
			}
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return leadEvaluations;
	}
}
