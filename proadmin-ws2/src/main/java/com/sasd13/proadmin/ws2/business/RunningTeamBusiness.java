package com.sasd13.proadmin.ws2.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IRunningTeamDAO;

/**
 * Created by Samir on 12/03/2016.
 */
public class RunningTeamBusiness implements IBusiness<RunningTeam> {

	@Autowired
	private IRunningTeamDAO dao;

	private Map<String, String[]> parameters;

	public RunningTeamBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(RunningTeam runningTeam) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(runningTeam.getRunning().getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { runningTeam.getRunning().getProject().getCode() });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { runningTeam.getRunning().getTeacher().getNumber() });
		parameters.put(EnumParameter.TEAM.getName(), new String[] { runningTeam.getTeam().getNumber() });
		parameters.put(EnumParameter.ACADEMICLEVEL.getName(), new String[] { runningTeam.getAcademicLevel().getCode() });

		if (!dao.select(parameters).isEmpty()) {
			throw new BusinessException("RunningTeam already exist");
		}
	}

	@Override
	public void verify(IUpdateWrapper<RunningTeam> updateWrapper) throws BusinessException {
		RunningTeamUpdateWrapper runningTeamUpdateWrapper = (RunningTeamUpdateWrapper) updateWrapper;

		if (runningTeamUpdateWrapper.getRunningYear() != runningTeamUpdateWrapper.getWrapped().getRunning().getYear() || !runningTeamUpdateWrapper.getProjectCode().equals(runningTeamUpdateWrapper.getWrapped().getRunning().getProject().getCode()) || !runningTeamUpdateWrapper.getTeacherNumber().equals(runningTeamUpdateWrapper.getWrapped().getRunning().getTeacher().getNumber()) || !runningTeamUpdateWrapper.getTeamNumber().equals(runningTeamUpdateWrapper.getWrapped().getTeam().getNumber()) || !runningTeamUpdateWrapper.getAcademicLevelCode().equals(runningTeamUpdateWrapper.getWrapped().getAcademicLevel().getCode())) {
			verify(runningTeamUpdateWrapper.getWrapped());
		}
	}
}
