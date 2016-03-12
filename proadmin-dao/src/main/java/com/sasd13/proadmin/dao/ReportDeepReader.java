package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.Parameter;

public class ReportDeepReader extends DeepReader<Report> {
	
	private LeadEvaluationDAO leadEvaluationDAO;
	private IndividualEvaluationDAO individualEvaluationDAO;
	
	public ReportDeepReader(IEntityDAO<Report> entityDAO, LeadEvaluationDAO leadEvaluationDAO, IndividualEvaluationDAO individualEvaluationDAO) {
		super(entityDAO);
		
		this.leadEvaluationDAO = leadEvaluationDAO;
		this.individualEvaluationDAO = individualEvaluationDAO;
	}
	
	@Override
	protected void retrieveData(Report report) {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put(Parameter.REPORT.getName(), new String[] { String.valueOf(report.getId()) });
		
		try {
			LeadEvaluation leadEvaluation = leadEvaluationDAO.select(parameters).get(0);
			report.getLeadEvaluation().setId(leadEvaluation.getId());
			report.getLeadEvaluation().setPlanningMark(leadEvaluation.getPlanningMark());
			report.getLeadEvaluation().setPlanningComment(leadEvaluation.getPlanningComment());
			report.getLeadEvaluation().setCommunicationMark(leadEvaluation.getCommunicationMark());
			report.getLeadEvaluation().setCommunicationComment(leadEvaluation.getCommunicationComment());
			report.getLeadEvaluation().setStudent(leadEvaluation.getStudent());
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		List<IndividualEvaluation> individualEvaluations = individualEvaluationDAO.select(parameters);
		IndividualEvaluation individualEvaluationToAdd;
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationToAdd = new IndividualEvaluation(report);
			individualEvaluationToAdd.setId(individualEvaluation.getId());
			individualEvaluationToAdd.setMark(individualEvaluation.getMark());
			individualEvaluationToAdd.setStudent(individualEvaluation.getStudent());
		}
	}
}
