package com.sasd13.proadmin.util;

import java.util.List;

import com.sasd13.proadmin.bean.project.Project;

public class Finder {

	public static Project findProjectByCode(String code, List<Project> projects) {
		for (Project project : projects) {
			if (project.getCode().equals(code)) {
				return project;
			}
		}

		return null;
	}
}
