package com.sasd13.proadmin.util.sorter.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.project.IProject;

public class ProjectSorter {

	public static void byCode(List<IProject> list) {
		byCode(list, true);
	}

	public static void byCode(List<IProject> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IProject>() {

				@Override
				public int compare(IProject item1, IProject item2) {
					if (byAsc) {
						return item1.getCode().compareTo(item2.getCode());
					} else {
						return item2.getCode().compareTo(item1.getCode());
					}
				}
			});
		}
	}

	public static void byDateCreation(List<IProject> list) {
		byCode(list, true);
	}

	public static void byDateCreation(List<IProject> list, final boolean byDesc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IProject>() {

				@Override
				public int compare(IProject item1, IProject item2) {
					if (!byDesc) {
						return item1.getDateCreation().compareTo(item2.getDateCreation());
					} else {
						return item2.getDateCreation().compareTo(item1.getDateCreation());
					}
				}
			});
		}
	}
}
