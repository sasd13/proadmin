package com.sasd13.proadmin.util.wrapper.update.running;

import java.util.List;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;

public interface IReportUpdateWrapper extends IUpdateWrapper<Report> {

	String getNumber();

	void setNumber(String number);

	IUpdateWrapper<LeadEvaluation> getLeadEvaluationUpdateWrapper();

	void setLeadEvaluationUpdateWrapper(IUpdateWrapper<LeadEvaluation> leadEvaluationUpdateWrapper);

	List<IUpdateWrapper<IndividualEvaluation>> getIndividualEvaluationUpdateWrappers();

	void setIndividualEvaluationUpdateWrappers(List<IUpdateWrapper<IndividualEvaluation>> individualEvaluationUpdateWrappers);
}
