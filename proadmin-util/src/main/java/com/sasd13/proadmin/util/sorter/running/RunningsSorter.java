package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.IRunning;

public class RunningsSorter {

	public static void byYear(List<IRunning> iRunnings) {
		byYear(iRunnings, true);
	}

	public static void byYear(List<IRunning> iRunnings, final boolean byDesc) {
		if (!iRunnings.isEmpty()) {
			Collections.sort(iRunnings, new Comparator<IRunning>() {

				@Override
				public int compare(IRunning running1, IRunning running2) {
					if (!byDesc) {
						return Integer.compare(running1.getYear(), running2.getYear());
					} else {
						return Integer.compare(running2.getYear(), running1.getYear());
					}
				}
			});
		}
	}

	public static void byProjectCode(List<IRunning> iRunnings) {
		byProjectCode(iRunnings, true);
	}

	public static void byProjectCode(List<IRunning> iRunnings, final boolean byAsc) {
		if (!iRunnings.isEmpty()) {
			Collections.sort(iRunnings, new Comparator<IRunning>() {

				@Override
				public int compare(IRunning running1, IRunning running2) {
					if (byAsc) {
						return running1.getProject().getCode().compareTo(running2.getProject().getCode());
					} else {
						return running2.getProject().getCode().compareTo(running1.getProject().getCode());
					}
				}
			});
		}
	}
}
