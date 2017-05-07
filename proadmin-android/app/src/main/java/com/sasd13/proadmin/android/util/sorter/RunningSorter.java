package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.bean.Running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RunningSorter {

    public static void byYear(List<Running> list) {
        byYear(list, true);
    }

    public static void byYear(List<Running> list, final boolean byDesc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<Running>() {

                @Override
                public int compare(Running item1, Running item2) {
                    if (!byDesc) {
                        return item1.getYear() - item2.getYear();
                    } else {
                        return item2.getYear() - item1.getYear();
                    }
                }
            });
        }
    }

    public static void byProjectCode(List<Running> list) {
        byProjectCode(list, true);
    }

    public static void byProjectCode(List<Running> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<Running>() {

                @Override
                public int compare(Running item1, Running item2) {
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
