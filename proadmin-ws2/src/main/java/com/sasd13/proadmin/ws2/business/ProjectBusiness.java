package com.sasd13.proadmin.ws2.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.error.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IProjectDAO;

public class ProjectBusiness implements IBusiness<Project> {

	@Autowired
	private IProjectDAO dao;

	private Map<String, String[]> parameters;

	public ProjectBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(Project project) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { project.getCode() });

		if (!dao.read(parameters).isEmpty()) {
			throw new BusinessException("Project already exist");
		}
	}

	@Override
	public void verify(IUpdateWrapper<Project> updateWrapper) throws BusinessException {
		ProjectUpdateWrapper projectUpdateWrapper = (ProjectUpdateWrapper) updateWrapper;

		if (!projectUpdateWrapper.getCode().equals(projectUpdateWrapper.getWrapped().getCode())) {
			verify(projectUpdateWrapper.getWrapped());
		}
	}
}
