package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public interface ILeadEvaluationUpdateWrapper extends IUpdateWrapper<LeadEvaluation> {

	String getReportNumber();

	void setReportNumber(String reportNumber);

	String getStudentNumber();

	void setStudentNumber(String studentNumber);
}
