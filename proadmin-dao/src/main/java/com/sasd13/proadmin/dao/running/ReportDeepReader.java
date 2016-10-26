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
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportDeepReader extends DeepReader<Report> {

	private ILeadEvaluationDAO leadEvaluationDAO;
	private IIndividualEvaluationDAO individualEvaluationDAO;

	public ReportDeepReader(IEntityDAO<Report> entityDAO, ILeadEvaluationDAO leadEvaluationDAO, IIndividualEvaluationDAO individualEvaluationDAO) {
		super(entityDAO);

		this.leadEvaluationDAO = leadEvaluationDAO;
		this.individualEvaluationDAO = individualEvaluationDAO;
	}

	@Override
	protected void retrieveData(Report report) throws DAOException {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put(EnumParameter.REPORT.getName(), new String[]{ String.valueOf(report.getId()) });

		LeadEvaluation leadEvaluation = leadEvaluationDAO.select(parameters).get(0);
		Binder.bind(report.getLeadEvaluation(), leadEvaluation);

		List<IndividualEvaluation> individualEvaluations = individualEvaluationDAO.select(parameters);
		IndividualEvaluation individualEvaluationToAdd;
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationToAdd = new IndividualEvaluation(report);
			Binder.bind(individualEvaluationToAdd, individualEvaluation);
		}
	}
}
