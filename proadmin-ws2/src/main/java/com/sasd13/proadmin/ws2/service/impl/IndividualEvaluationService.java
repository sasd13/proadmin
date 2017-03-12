package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.ws2.db.dto.IndividualEvaluationDTO;
import com.sasd13.proadmin.ws2.service.IIndividualEvaluationService;
import com.sasd13.proadmin.ws2.util.adapter.IndividualEvaluationDTOAdapter;

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
		IndividualEvaluationDTOAdapter adapter = new IndividualEvaluationDTOAdapter();

		for (IndividualEvaluationDTO dto : dtos) {
			individualEvaluations.add(adapter.adapt(dto));
		}

		return individualEvaluations;
	}
}
