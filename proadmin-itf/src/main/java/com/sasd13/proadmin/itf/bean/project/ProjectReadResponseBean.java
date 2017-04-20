package com.sasd13.proadmin.itf.bean.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.itf.ResponseBean;

public class ProjectReadResponseBean extends ResponseBean {

	private List<Project> data;

	public ProjectReadResponseBean() {
		data = new ArrayList<Project>();
	}

	public List<Project> getData() {
		return data;
	}

	public void setData(List<Project> data) {
		this.data = data;
	}
}
