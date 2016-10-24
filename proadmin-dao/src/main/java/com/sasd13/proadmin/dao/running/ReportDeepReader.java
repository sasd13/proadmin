package com.sasd13.proadmin.dao.running;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportDeepReader extends DeepReader<Report> {

	private ILeadEvaluationDAO iLeadEvaluationDAO;
	private IIndividualEvaluationDAO iIndividualEvaluationDAO;

	public ReportDeepReader(IEntityDAO<Report> entityDAO, ILeadEvaluationDAO iLeadEvaluationDAO, IIndividualEvaluationDAO iIndividualEvaluationDAO) {
		super(entityDAO);

		this.iLeadEvaluationDAO = iLeadEvaluationDAO;
		this.iIndividualEvaluationDAO = iIndividualEvaluationDAO;
	}

	@Override
	protected void retrieveData(Report report) throws DAOException {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put(EnumParameter.REPORT.getName(), new String[] { String.valueOf(report.getId()) });

		LeadEvaluation leadEvaluation = iLeadEvaluationDAO.select(parameters).get(0);
		report.getLeadEvaluation().setId(leadEvaluation.getId());
		report.getLeadEvaluation().setPlanningMark(leadEvaluation.getPlanningMark());
		report.getLeadEvaluation().setPlanningComment(leadEvaluation.getPlanningComment());
		report.getLeadEvaluation().setCommunicationMark(leadEvaluation.getCommunicationMark());
		report.getLeadEvaluation().setCommunicationComment(leadEvaluation.getCommunicationComment());
		report.getLeadEvaluation().setStudent(leadEvaluation.getStudent());

		List<IndividualEvaluation> individualEvaluations = iIndividualEvaluationDAO.select(parameters);
		IndividualEvaluation individualEvaluationToAdd;
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationToAdd = new IndividualEvaluation(report);
			individualEvaluationToAdd.setId(individualEvaluation.getId());
			individualEvaluationToAdd.setMark(individualEvaluation.getMark());
			individualEvaluationToAdd.setStudent(individualEvaluation.getStudent());
		}
	}
}
