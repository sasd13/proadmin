package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.IndividualEvaluation;
import com.sasd13.proadmin.backend.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.backend.dao.dto.IndividualEvaluationDTO;
import com.sasd13.proadmin.backend.service.IIndividualEvaluationService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.IndividualEvaluationAdapterD2B;

@Service
public class IndividualEvaluationService implements IIndividualEvaluationService {

	@Autowired
	private IIndividualEvaluationDAO individualEvaluationDAO;

	@Override
	public void create(List<IndividualEvaluation> individualEvaluations) {
		individualEvaluationDAO.create(individualEvaluations);
	}

	@Override
	public void update(List<IndividualEvaluation> individualEvaluations) {
		individualEvaluationDAO.update(individualEvaluations);
	}

	@Override
	public void delete(List<IndividualEvaluation> individualEvaluations) {
		individualEvaluationDAO.delete(individualEvaluations);
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) {
		List<IndividualEvaluation> list = new ArrayList<>();

		List<IndividualEvaluationDTO> dtos = individualEvaluationDAO.read(parameters);
		IndividualEvaluationAdapterD2B adapter = new IndividualEvaluationAdapterD2B();

		for (IndividualEvaluationDTO dto : dtos) {
			list.add(adapter.adapt(dto));
		}

		return list;
	}
}
