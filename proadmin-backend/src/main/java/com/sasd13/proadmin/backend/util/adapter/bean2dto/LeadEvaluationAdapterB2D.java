package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.LeadEvaluation;
import com.sasd13.proadmin.backend.dao.dto.LeadEvaluationDTO;
import com.sasd13.proadmin.backend.dao.dto.ReportDTO;
import com.sasd13.proadmin.backend.dao.dto.StudentDTO;

public class LeadEvaluationAdapterB2D implements IAdapter<LeadEvaluation, LeadEvaluationDTO> {

	@Override
	public LeadEvaluationDTO adapt(LeadEvaluation s) {
		LeadEvaluationDTO t = new LeadEvaluationDTO();

		t.setId(s.getId());
		t.setPlanningMark(s.getPlanningMark());
		t.setPlanningComment(s.getPlanningComment());
		t.setCommunicationMark(s.getCommunicationMark());
		t.setCommunicationComment(s.getCommunicationComment());

		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setId(s.getReport().getId());
		t.setReport(reportDTO);

		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(s.getStudent().getId());
		t.setStudent(studentDTO);

		return t;
	}
}
