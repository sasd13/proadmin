package com.sasd13.proadmin.itf.bean.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.proadmin.itf.ResponseBean;

public class ProjectReadResponseBean extends ResponseBean {

	private List<ProjectBean> data;

	public ProjectReadResponseBean() {
		data = new ArrayList<>();
	}

	public List<ProjectBean> getData() {
		return data;
	}

	public void setData(List<ProjectBean> data) {
		this.data = data;
	}
}
