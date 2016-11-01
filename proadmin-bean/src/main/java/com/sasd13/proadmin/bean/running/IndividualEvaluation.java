package com.sasd13.proadmin.bean.running;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndividualEvaluation extends Evaluation {

	private float mark;

	public IndividualEvaluation(@JsonProperty("report") Report report) {
		super(report);

		report.addIndividualEvaluation(this);
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("IndividualEvaluation [");
		builder.append("mark=" + getMark());
		builder.append("]");

		return builder.toString();
	}
}
