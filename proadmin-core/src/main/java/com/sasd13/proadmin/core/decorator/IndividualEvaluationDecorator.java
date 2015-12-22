package com.sasd13.proadmin.core.decorator;

import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;

public class IndividualEvaluationDecorator extends IndividualEvaluation {
	
	private IndividualEvaluation individualEvaluation;
	private boolean deleted;
	
	public IndividualEvaluationDecorator(IndividualEvaluation individualEvaluation) {
		this.individualEvaluation = individualEvaluation;
	}
	
	public IndividualEvaluation getIndividualEvaluation() {
		return individualEvaluation;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
