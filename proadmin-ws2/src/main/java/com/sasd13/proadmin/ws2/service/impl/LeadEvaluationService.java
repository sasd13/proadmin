package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.ws2.db.dto.LeadEvaluationDTO;
import com.sasd13.proadmin.ws2.service.ILeadEvaluationService;
import com.sasd13.proadmin.ws2.util.adapter.LeadEvaluationDTOAdapter;

@Service
public class LeadEvaluationService implements ILeadEvaluationService {

	@Autowired
	private ILeadEvaluationDAO leadEvaluationDAO;

	@Override
	public void create(LeadEvaluation leadEvaluation) {
		leadEvaluationDAO.create(leadEvaluation);
	}

	@Override
	public void update(List<LeadEvaluationUpdateWrapper> updateWrappers) {
		leadEvaluationDAO.update(updateWrappers);
	}

	public void delete(List<LeadEvaluation> leadEvaluations) {
		leadEvaluationDAO.delete(leadEvaluations);
	};

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) {
		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		List<LeadEvaluationDTO> dtos = leadEvaluationDAO.read(parameters);
		LeadEvaluationDTOAdapter adapter = new LeadEvaluationDTOAdapter();

		for (LeadEvaluationDTO dto : dtos) {
			leadEvaluations.add(adapter.adapt(dto));
		}

		return leadEvaluations;
	}
}
