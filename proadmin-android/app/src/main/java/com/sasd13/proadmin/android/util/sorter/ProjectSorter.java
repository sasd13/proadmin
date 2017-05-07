package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.bean.Project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProjectSorter {

    public static void byDateCreationAndCode(List<Project> list) {
        byDateCreationAndCode(list, true);
    }

    public static void byDateCreationAndCode(List<Project> list, final boolean byDesc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<Project>() {

                @Override
                public int compare(Project item1, Project item2) {
                    int value;

                    if (!byDesc) {
                        value = item1.getDateCreation().compareTo(item2.getDateCreation());
                    } else {
                        value = item2.getDateCreation().compareTo(item1.getDateCreation());
                    }

                    if (value == 0) {
                        value = item1.getCode().compareTo(item2.getCode());
                    }

                    return value;
                }
            });
        }
    }
}
