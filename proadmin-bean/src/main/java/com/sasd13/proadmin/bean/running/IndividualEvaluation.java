package com.sasd13.proadmin.bean.running;

public class IndividualEvaluation extends Evaluation {
	
	private float mark;
	
	public IndividualEvaluation() {}
	
	public IndividualEvaluation(Report report) {
		super(report);
		
		report.getIndividualEvaluations().add(this);
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
		builder.append("id=" + getId());
		builder.append(", mark=" + getMark());
		builder.append("]");
		
		return builder.toString();
	}
}
