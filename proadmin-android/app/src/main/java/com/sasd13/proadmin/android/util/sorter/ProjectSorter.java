package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.bean.Project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProjectSorter {

    public static void byCode(List<Project> list) {
        byCode(list, true);
    }

    public static void byCode(List<Project> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<Project>() {

                @Override
                public int compare(Project item1, Project item2) {
                    if (byAsc) {
                        return item1.getCode().compareTo(item2.getCode());
                    } else {
                        return item2.getCode().compareTo(item1.getCode());
                    }
                }
            });
        }
    }

    public static void byDateCreation(List<Project> list) {
        byCode(list, true);
    }

    public static void byDateCreation(List<Project> list, final boolean byDesc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<Project>() {

                @Override
                public int compare(Project item1, Project item2) {
                    if (!byDesc) {
                        return item1.getDateCreation().compareTo(item2.getDateCreation());
                    } else {
                        return item2.getDateCreation().compareTo(item1.getDateCreation());
                    }
                }
            });
        }
    }
}
