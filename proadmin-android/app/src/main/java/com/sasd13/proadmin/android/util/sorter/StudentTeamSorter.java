package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.bean.StudentTeam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentTeamSorter {

    public static void byStudentIntermediary(List<StudentTeam> list) {
        byStudentIntermediary(list, true);
    }

    public static void byStudentIntermediary(List<StudentTeam> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<StudentTeam>() {

                @Override
                public int compare(StudentTeam item1, StudentTeam item2) {
                    if (byAsc) {
                        return item1.getStudent().getIntermediary().compareTo(item2.getStudent().getIntermediary());
                    } else {
                        return item2.getStudent().getIntermediary().compareTo(item1.getStudent().getIntermediary());
                    }
                }
            });
        }
    }
}
