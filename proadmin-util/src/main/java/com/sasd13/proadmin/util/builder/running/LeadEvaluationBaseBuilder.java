package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.builder.member.StudentBaseBuilder;

public class LeadEvaluationBaseBuilder implements IBuilder<LeadEvaluation> {

	private String reportNumber, studentNumber;

	public LeadEvaluationBaseBuilder(String studentNumber) {
		this(null, studentNumber);
	}

	public LeadEvaluationBaseBuilder(String reportNumber, String studentNumber) {
		this.reportNumber = reportNumber;
		this.studentNumber = studentNumber;
	}

	@Override
	public LeadEvaluation build() {
		LeadEvaluation leadEvaluation = new LeadEvaluation();
		leadEvaluation.setStudent(new StudentBaseBuilder(studentNumber).build());

		if (reportNumber != null) {
			Report report = new Report();
			report.setNumber(reportNumber);

			leadEvaluation.setReport(report);
		}

		return leadEvaluation;
	}
}
