package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.IRunning;

public class RunningSorter {

	public static void byYear(List<IRunning> list) {
		byYear(list, true);
	}

	public static void byYear(List<IRunning> list, final boolean byDesc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IRunning>() {

				@Override
				public int compare(IRunning item1, IRunning item2) {
					if (!byDesc) {
						return Integer.compare(item1.getYear(), item2.getYear());
					} else {
						return Integer.compare(item2.getYear(), item1.getYear());
					}
				}
			});
		}
	}

	public static void byProjectCode(List<IRunning> list) {
		byProjectCode(list, true);
	}

	public static void byProjectCode(List<IRunning> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IRunning>() {

				@Override
				public int compare(IRunning item1, IRunning item2) {
					if (byAsc) {
						return item1.getProject().getCode().compareTo(item2.getProject().getCode());
					} else {
						return item2.getProject().getCode().compareTo(item1.getProject().getCode());
					}
				}
			});
		}
	}
}
