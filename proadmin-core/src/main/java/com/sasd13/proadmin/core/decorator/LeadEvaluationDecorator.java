package com.sasd13.proadmin.core.decorator;

import com.sasd13.proadmin.core.bean.running.LeadEvaluation;

public class LeadEvaluationDecorator extends LeadEvaluation {
	
	private LeadEvaluation leadEvaluation;
	private boolean deleted;
	
	public LeadEvaluationDecorator(LeadEvaluation leadEvaluation) {
		this.leadEvaluation = leadEvaluation;
	}
	
	public LeadEvaluation getLeadEvaluation() {
		return leadEvaluation;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
