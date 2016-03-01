package com.sasd13.proadmin.core.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.core.bean.running.LeadEvaluation;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.util.Parameter;

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
			LeadEvaluation leadEvaluationFromDB = leadEvaluationDAO.select(parameters).get(0);
			
			LeadEvaluation leadEvaluation = report.getLeadEvaluation();
			leadEvaluation.setId(leadEvaluationFromDB.getId());
			leadEvaluation.setPlanningMark(leadEvaluationFromDB.getPlanningMark());
			leadEvaluation.setPlanningComment(leadEvaluationFromDB.getPlanningComment());
			leadEvaluation.setCommunicationMark(leadEvaluationFromDB.getCommunicationMark());
			leadEvaluation.setCommunicationComment(leadEvaluationFromDB.getCommunicationComment());
			leadEvaluation.setStudent(leadEvaluationFromDB.getStudent());
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		List<IndividualEvaluation> individualEvaluationsFromDB = individualEvaluationDAO.select(parameters);
		
		IndividualEvaluation individualEvaluation;
		for (IndividualEvaluation individualEvaluationFromDB : individualEvaluationsFromDB) {
			individualEvaluation = new IndividualEvaluation(report);
			individualEvaluation.setId(individualEvaluationFromDB.getId());
			individualEvaluation.setMark(individualEvaluationFromDB.getMark());
			individualEvaluation.setStudent(individualEvaluationFromDB.getStudent());
		}
	}
}
