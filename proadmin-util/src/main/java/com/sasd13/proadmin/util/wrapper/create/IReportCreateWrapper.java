package com.sasd13.proadmin.util.wrapper.create;

import java.util.List;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;

public interface IReportCreateWrapper {

	Report getReport();

	void setReport(Report report);

	LeadEvaluation getLeadEvaluation();

	void setLeadEvaluation(LeadEvaluation leadEvaluation);

	List<IndividualEvaluation> getIndividualEvaluations();

	void setIndividualEvaluations(List<IndividualEvaluation> individualEvaluations);
}
