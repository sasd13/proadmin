package com.sasd13.proadmin.util.sorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntegersSorter {

	public static void byAsc(List<Integer> list) {
		sort(list, true);
	}

	public static void byDesc(List<Integer> list) {
		sort(list, false);
	}

	public static void sort(List<Integer> list, final boolean byAsc) {
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer integer1, Integer integer2) {
				if (byAsc) {
					return integer1.compareTo(integer2);
				} else {
					return integer2.compareTo(integer1);
				}
			}
		});
	}
}
