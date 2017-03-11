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
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IIndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.ws2.db.dto.IndividualEvaluationDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.IndividualEvaluationDTOAdapter;
import com.sasd13.proadmin.ws2.service.IService;

public class IndividualEvaluationService implements IService<IndividualEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(IndividualEvaluationService.class);

	@Autowired
	private IIndividualEvaluationDAO dao;

	@Override
	public void create(IndividualEvaluation individualEvaluation) {
		LOGGER.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(IUpdateWrapper<IndividualEvaluation> updateWrapper) {
		LOGGER.info("update : reportNumber=" + ((IIndividualEvaluationUpdateWrapper) updateWrapper).getReportNumber() + ", studentNumber=" + ((IIndividualEvaluationUpdateWrapper) updateWrapper).getStudentNumber());

		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) {
		LOGGER.info("delete unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();
		IndividualEvaluationDTOAdapter adapter = new IndividualEvaluationDTOAdapter();

		try {
			List<Serializable> results = dao.select(parameters);

			for (Serializable result : results) {
				individualEvaluations.add(adapter.adapt((IndividualEvaluationDTO) result));
			}
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
		IndividualEvaluationDTOAdapter adapter = new IndividualEvaluationDTOAdapter();

		try {
			List<Serializable> results = dao.selectAll();

			for (Serializable result : results) {
				individualEvaluations.add(adapter.adapt((IndividualEvaluationDTO) result));
			}
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return individualEvaluations;
	}
}
