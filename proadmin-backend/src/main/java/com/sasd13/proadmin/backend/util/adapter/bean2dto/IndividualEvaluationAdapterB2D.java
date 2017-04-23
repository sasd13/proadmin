package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.IndividualEvaluation;
import com.sasd13.proadmin.backend.dao.dto.IndividualEvaluationDTO;
import com.sasd13.proadmin.backend.dao.dto.ReportDTO;
import com.sasd13.proadmin.backend.dao.dto.StudentDTO;

public class IndividualEvaluationAdapterB2D implements IAdapter<IndividualEvaluation, IndividualEvaluationDTO> {

	@Override
	public IndividualEvaluationDTO adapt(IndividualEvaluation s) {
		IndividualEvaluationDTO t = new IndividualEvaluationDTO();

		t.setId(s.getId());
		t.setMark(s.getMark());

		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setId(s.getReport().getId());
		t.setReport(reportDTO);

		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(s.getStudent().getId());
		t.setStudent(studentDTO);

		return t;
	}
}
