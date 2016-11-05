package com.sasd13.proadmin.util.sorter.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.project.Project;

public class ProjectsSorter {

	public static void byCode(List<Project> projects) {
		byCode(projects, true);
	}

	public static void byCode(List<Project> projects, final boolean byAsc) {
		Collections.sort(projects, new Comparator<Project>() {

			@Override
			public int compare(Project project1, Project project2) {
				if (byAsc) {
					return project1.getCode().compareTo(project2.getCode());
				} else {
					return project2.getCode().compareTo(project1.getCode());
				}
			}
		});
	}

	public static void byDateCreation(List<Project> projects) {
		byCode(projects, false);
	}

	public static void byDateCreation(List<Project> projects, final boolean byAsc) {
		Collections.sort(projects, new Comparator<Project>() {

			@Override
			public int compare(Project project1, Project project2) {
				if (byAsc) {
					return project1.getDateCreation().compareTo(project2.getDateCreation());
				} else {
					return project2.getDateCreation().compareTo(project1.getDateCreation());
				}
			}
		});
	}
}
