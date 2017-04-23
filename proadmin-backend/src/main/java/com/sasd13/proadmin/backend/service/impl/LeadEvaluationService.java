package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.LeadEvaluation;
import com.sasd13.proadmin.backend.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.backend.dao.dto.LeadEvaluationDTO;
import com.sasd13.proadmin.backend.service.ILeadEvaluationService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.LeadEvaluationAdapterD2B;

@Service
public class LeadEvaluationService implements ILeadEvaluationService {

	@Autowired
	private ILeadEvaluationDAO leadEvaluationDAO;

	@Override
	public void create(LeadEvaluation leadEvaluation) {
		leadEvaluationDAO.create(leadEvaluation);
	}

	@Override
	public void update(LeadEvaluation leadEvaluation) {
		leadEvaluationDAO.update(leadEvaluation);
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		leadEvaluationDAO.delete(leadEvaluation);
	}

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) {
		List<LeadEvaluation> list = new ArrayList<>();

		List<LeadEvaluationDTO> dtos = leadEvaluationDAO.read(parameters);
		LeadEvaluationAdapterD2B adapter = new LeadEvaluationAdapterD2B();

		for (LeadEvaluationDTO dto : dtos) {
			list.add(adapter.adapt(dto));
		}

		return list;
	}
}
