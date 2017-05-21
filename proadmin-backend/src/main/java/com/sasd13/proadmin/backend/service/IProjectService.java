package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.project.ProjectBean;

public interface IProjectService {

	void create(ProjectBean projectBean);

	void update(ProjectBean projectBean);

	void delete(ProjectBean projectBean);

	List<ProjectBean> read(Map<String, Object> criterias);
}
