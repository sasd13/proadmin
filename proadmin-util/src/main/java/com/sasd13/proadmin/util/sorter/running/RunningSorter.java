package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.Running;

public class RunningSorter {
	
	public static void byYear(List<Running> runnings, final boolean byDesc) {
		Collections.sort(runnings, new Comparator<Running>() {

			@Override
			public int compare(Running running1, Running running2) {
				int year1 = running1.getYear();
				int year2 = running2.getYear();
				
				if (byDesc) {
					return (year1 > year2)
							? -1
							: (year1 == year2)
								? 0
								: 1;
				} else {
					return (year1 < year2)
							? -1
							: (year1 == year2)
								? 0
								: 1;
				}
			}
		});
	}
}
