package com.sasd13.proadmin.util.sorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.level.AcademicLevel;

public class AcademicLevelsSorter {

	public static void byCode(List<AcademicLevel> academicLevels) {
		byCode(academicLevels, true);
	}

	public static void byCode(List<AcademicLevel> academicLevels, final boolean byAsc) {
		if (!academicLevels.isEmpty()) {
			Collections.sort(academicLevels, new Comparator<AcademicLevel>() {

				@Override
				public int compare(AcademicLevel academicLevel1, AcademicLevel academicLevel2) {
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
