package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.backend.dao.dto.IndividualEvaluationDTO;
import com.sasd13.proadmin.backend.service.IIndividualEvaluationService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.IndividualEvaluationD2B;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

@Service
public class IndividualEvaluationService implements IIndividualEvaluationService {

	@Autowired
	private IIndividualEvaluationDAO individualEvaluationDAO;

	@Override
	public void create(IndividualEvaluation individualEvaluation) {
		individualEvaluationDAO.create(individualEvaluation);
	}

	@Override
	public void update(List<IndividualEvaluationUpdateWrapper> updateWrappers) {
		individualEvaluationDAO.update(updateWrappers);
	}

	@Override
	public void delete(List<IndividualEvaluation> individualEvaluations) {
		individualEvaluationDAO.delete(individualEvaluations);
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) {
		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		List<IndividualEvaluationDTO> dtos = individualEvaluationDAO.read(parameters);
		IndividualEvaluationD2B adapter = new IndividualEvaluationD2B();

		for (IndividualEvaluationDTO dto : dtos) {
			individualEvaluations.add(adapter.adapt(dto));
		}

		return individualEvaluations;
	}
}
