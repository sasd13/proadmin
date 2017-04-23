package com.sasd13.proadmin.util.sorter.level;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.level.IAcademicLevel;

public class AcademicLevelsSorter {

	public static void byCode(List<IAcademicLevel> iAcademicLevels) {
		byCode(iAcademicLevels, true);
	}

	public static void byCode(List<IAcademicLevel> iAcademicLevels, final boolean byAsc) {
		if (!iAcademicLevels.isEmpty()) {
			Collections.sort(iAcademicLevels, new Comparator<IAcademicLevel>() {

				@Override
				public int compare(IAcademicLevel academicLevel1, IAcademicLevel academicLevel2) {
					if (byAsc) {
						return academicLevel1.getCode().compareTo(academicLevel2.getCode());
					} else {
						return academicLevel2.getCode().compareTo(academicLevel1.getCode());
					}
				}
			});
		}
	}
}
