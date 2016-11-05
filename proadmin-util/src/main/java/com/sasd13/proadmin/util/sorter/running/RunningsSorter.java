package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.Running;

public class RunningsSorter {

	public static void byYear(List<Running> runnings) {
		byYear(runnings, false);
	}

	public static void byYear(List<Running> runnings, final boolean byDesc) {
		Collections.sort(runnings, new Comparator<Running>() {

			@Override
			public int compare(Running running1, Running running2) {
				if (byDesc) {
					return Integer.compare(running2.getYear(), running1.getYear());
				} else {
					return Integer.compare(running1.getYear(), running2.getYear());
				}
			}
		});
	}
	
	public static void byProjectCode(List<Running> runnings) {
		byProjectCode(runnings, false);
	}

	public static void byProjectCode(List<Running> runnings, final boolean byAsc) {
		Collections.sort(runnings, new Comparator<Running>() {

			@Override
			public int compare(Running running1, Running running2) {
				if (byAsc) {
					return running1.getProject().getCode().compareTo(running2.getProject().getCode());
				} else {
					return running2.getProject().getCode().compareTo(running1.getProject().getCode());
				}
			}
		});
	}
}
