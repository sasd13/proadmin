package com.sasd13.proadmin.bean.running;

import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.bean.project.IProject;

public interface IRunning {

	IProject getProject();

	ITeacher getTeacher();

	int getYear();
}
