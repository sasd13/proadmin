package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.model.AcademicLevel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AcademicLevelSorter {

    public static void byCode(List<AcademicLevel> list) {
        byCode(list, true);
    }

    public static void byCode(List<AcademicLevel> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<AcademicLevel>() {

                @Override
                public int compare(AcademicLevel item1, AcademicLevel item2) {
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
