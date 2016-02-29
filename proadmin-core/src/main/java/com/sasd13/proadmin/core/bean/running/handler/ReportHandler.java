package com.sasd13.proadmin.core.bean.running.handler;

import java.util.List;

import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.core.bean.running.LeadEvaluation;
import com.sasd13.proadmin.core.bean.running.Report;

public class ReportHandler {
	
	public static void setLeadEvaluationToReport(LeadEvaluation leadEvaluation, Report report) {
		LeadEvaluation leadEvaluationTarget = report.getLeadEvaluation();
		
		leadEvaluationTarget.setId(leadEvaluation.getId());
		leadEvaluationTarget.setPlanningMark(leadEvaluation.getPlanningMark());
		leadEvaluationTarget.setPlanningComment(leadEvaluation.getPlanningComment());
		leadEvaluationTarget.setCommunicationMark(leadEvaluation.getCommunicationMark());
		leadEvaluationTarget.setCommunicationComment(leadEvaluation.getCommunicationComment());
		leadEvaluationTarget.setStudent(leadEvaluation.getStudent());
	}
	
	public static void setIndividualEvaluationsToReport(List<IndividualEvaluation> individualEvaluations, Report report) {
		List<IndividualEvaluation> individualEvaluationsTarget = report.getIndividualEvaluations();
		
		individualEvaluationsTarget.clear();
		individualEvaluationsTarget.addAll(individualEvaluations);
	}
}
