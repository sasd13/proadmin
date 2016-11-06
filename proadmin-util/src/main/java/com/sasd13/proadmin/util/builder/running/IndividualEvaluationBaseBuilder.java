package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.builder.member.StudentBaseBuilder;

public class IndividualEvaluationBaseBuilder implements IBuilder<IndividualEvaluation> {

	private String reportNumber, studentNumber;

	public IndividualEvaluationBaseBuilder(String studentNumber) {
		this(null, studentNumber);
	}

	public IndividualEvaluationBaseBuilder(String reportNumber, String studentNumber) {
		this.reportNumber = reportNumber;
		this.studentNumber = studentNumber;
	}

	@Override
	public IndividualEvaluation build() {
		IndividualEvaluation individualEvaluation = new IndividualEvaluation();
		individualEvaluation.setStudent(new StudentBaseBuilder(studentNumber).build());

		if (reportNumber != null) {
			Report report = new Report();
			report.setNumber(reportNumber);

			individualEvaluation.setReport(report);
		}

		return individualEvaluation;
	}
}
