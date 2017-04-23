package com.sasd13.proadmin.util.sorter.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.project.IProject;

public class ProjectsSorter {

	public static void byCode(List<IProject> iProjects) {
		byCode(iProjects, true);
	}

	public static void byCode(List<IProject> iProjects, final boolean byAsc) {
		if (!iProjects.isEmpty()) {
			Collections.sort(iProjects, new Comparator<IProject>() {

				@Override
				public int compare(IProject project1, IProject project2) {
					if (byAsc) {
						return project1.getCode().compareTo(project2.getCode());
					} else {
						return project2.getCode().compareTo(project1.getCode());
					}
				}
			});
		}
	}

	public static void byDateCreation(List<IProject> iProjects) {
		byCode(iProjects, true);
	}

	public static void byDateCreation(List<IProject> iProjects, final boolean byDesc) {
		if (!iProjects.isEmpty()) {
			Collections.sort(iProjects, new Comparator<IProject>() {

				@Override
				public int compare(IProject project1, IProject project2) {
					if (!byDesc) {
						return project1.getDateCreation().compareTo(project2.getDateCreation());
					} else {
						return project2.getDateCreation().compareTo(project1.getDateCreation());
					}
				}
			});
		}
	}
}
