package com.sasd13.proadmin.util.wrapper.update.project;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.project.Project;

public interface IProjectUpdateWrapper extends IUpdateWrapper<Project> {

	String getCode();

	void setCode(String code);
}
