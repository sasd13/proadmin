package com.sasd13.proadmin.ws2.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.error.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IRunningDAO;

/**
 * Created by Samir on 12/03/2016.
 */
public class RunningBusiness implements IBusiness<Running> {

	@Autowired
	private IRunningDAO dao;

	private Map<String, String[]> parameters;

	public RunningBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(Running running) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(running.getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { running.getProject().getCode() });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { running.getTeacher().getNumber() });

		if (!dao.read(parameters).isEmpty()) {
			throw new BusinessException("Running already exist");
		}
	}

	@Override
	public void verify(IUpdateWrapper<Running> updateWrapper) throws BusinessException {
		RunningUpdateWrapper runningUpdateWrapper = (RunningUpdateWrapper) updateWrapper;

		if (runningUpdateWrapper.getYear() != runningUpdateWrapper.getWrapped().getYear() || !runningUpdateWrapper.getProjectCode().equals(runningUpdateWrapper.getWrapped().getProject().getCode()) || !runningUpdateWrapper.getTeacherNumber().equals(runningUpdateWrapper.getWrapped().getTeacher().getNumber())) {
			verify(runningUpdateWrapper.getWrapped());
		}
	}
}
