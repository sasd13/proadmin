package com.sasd13.proadmin.util.wrapper.update.project;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.project.IProject;

public class ProjectUpdateWrapper implements IUpdateWrapper<IProject> {

	private IProject iProject;
	private String code;

	@Override
	public IProject getWrapped() {
		return iProject;
	}

	@Override
	public void setWrapped(IProject iProject) {
		this.iProject = iProject;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
