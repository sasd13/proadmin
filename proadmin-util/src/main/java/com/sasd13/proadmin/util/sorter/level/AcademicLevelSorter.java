package com.sasd13.proadmin.util.sorter.level;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.level.IAcademicLevel;

public class AcademicLevelSorter {

	public static void byCode(List<IAcademicLevel> list) {
		byCode(list, true);
	}

	public static void byCode(List<IAcademicLevel> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IAcademicLevel>() {

				@Override
				public int compare(IAcademicLevel item1, IAcademicLevel item2) {
					if (byAsc) {
						return item1.getCode().compareTo(item2.getCode());
					} else {
						return item2.getCode().compareTo(item1.getCode());
					}
				}
			});
		}
	}
}
