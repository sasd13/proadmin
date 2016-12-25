package com.sasd13.proadmin.ws.service;

import com.sasd13.javaex.service.IDeepReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.ws.service.member.StudentTeamReadService;
import com.sasd13.proadmin.ws.service.running.IndividualEvaluationReadService;
import com.sasd13.proadmin.ws.service.running.LeadEvaluationReadService;
import com.sasd13.proadmin.ws.service.running.ReportReadService;
import com.sasd13.proadmin.ws.service.running.RunningReadService;
import com.sasd13.proadmin.ws.service.running.RunningTeamReadService;

public class DeepReadServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> IDeepReadService<T> make(Class<T> mClass) throws ServiceException {
		if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (IDeepReadService<T>) new StudentTeamReadService();
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (IDeepReadService<T>) new RunningReadService();
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (IDeepReadService<T>) new RunningTeamReadService();
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (IDeepReadService<T>) new ReportReadService();
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (IDeepReadService<T>) new LeadEvaluationReadService();
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (IDeepReadService<T>) new IndividualEvaluationReadService();
		} else {
			throw new ServiceException("Entity " + mClass.getName() + " has no deepReadService");
		}
	}
}
