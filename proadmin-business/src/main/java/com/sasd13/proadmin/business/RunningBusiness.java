package com.sasd13.proadmin.business;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

/**
 * Created by Samir on 12/03/2016.
 */
public class RunningBusiness implements IBusiness<Running> {

	private Map<String, String[]> parameters;

	public RunningBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(DAO dao, Running running) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(running.getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { running.getProject().getCode() });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { running.getTeacher().getNumber() });

		if (!dao.getSession(Running.class).select(parameters).isEmpty()) {
			throw new BusinessException("Running already exist");
		}
	}

	@Override
	public void verify(DAO dao, IUpdateWrapper<Running> updateWrapper) throws BusinessException {
		RunningUpdateWrapper runningUpdateWrapper = (RunningUpdateWrapper) updateWrapper;

		if (runningUpdateWrapper.getYear() != runningUpdateWrapper.getWrapped().getYear()
				|| !runningUpdateWrapper.getProjectCode().equals(runningUpdateWrapper.getWrapped().getProject().getCode())
				|| !runningUpdateWrapper.getTeacherNumber().equals(runningUpdateWrapper.getWrapped().getTeacher().getNumber())) {
			verify(dao, runningUpdateWrapper.getWrapped());
		}
	}
}
