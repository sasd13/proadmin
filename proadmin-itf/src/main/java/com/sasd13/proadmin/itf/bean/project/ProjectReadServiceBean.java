package com.sasd13.proadmin.itf.bean.project;

import java.util.List;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.itf.context.dated.DatedContext;

public class ProjectReadServiceBean {

	private DatedContext context;
	private List<Project> projects;
	
	public DatedContext getContext() {
		return context;
	}
	
	public void setContext(DatedContext context) {
		this.context = context;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}
