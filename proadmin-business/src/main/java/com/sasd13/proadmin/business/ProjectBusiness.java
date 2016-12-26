package com.sasd13.proadmin.business;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;

public class ProjectBusiness implements IBusiness<Project> {

	private Map<String, String[]> parameters;

	public ProjectBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(DAO dao, Project project) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { project.getCode() });

		if (dao.getSession(Project.class).select(parameters) != null) {
			throw new BusinessException("Project already exist");
		}
	}

	@Override
	public void verify(DAO dao, IUpdateWrapper<Project> updateWrapper) throws BusinessException {
		ProjectUpdateWrapper projectUpdateWrapper = (ProjectUpdateWrapper) updateWrapper;

		if (!projectUpdateWrapper.getCode().equals(projectUpdateWrapper.getWrapped().getCode())) {
			verify(dao, projectUpdateWrapper.getWrapped());
		}
	}
}
