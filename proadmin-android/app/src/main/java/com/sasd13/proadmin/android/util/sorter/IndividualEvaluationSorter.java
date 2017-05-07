package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.bean.IndividualEvaluation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IndividualEvaluationSorter {

    public static void byStudentNumber(List<IndividualEvaluation> list) {
        byStudentNumber(list, true);
    }

    public static void byStudentNumber(List<IndividualEvaluation> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<IndividualEvaluation>() {

                @Override
                public int compare(IndividualEvaluation item1, IndividualEvaluation item2) {
                    if (byAsc) {
                        return item1.getStudent().getIntermediary().compareTo(item2.getStudent().getIntermediary());
                    } else {
                        return item2.getStudent().getIntermediary().compareTo(item1.getStudent().getIntermediary());
                    }
                }
            });
        }
    }

    public static void byStudentFullName(List<IndividualEvaluation> list) {
        byStudentFullName(list, true);
    }

    public static void byStudentFullName(List<IndividualEvaluation> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<IndividualEvaluation>() {

                @Override
                public int compare(IndividualEvaluation item1, IndividualEvaluation item2) {
                    if (byAsc) {
                        return item1.getStudent().getFullName().compareTo(item2.getStudent().getFullName());
                    } else {
                        return item2.getStudent().getFullName().compareTo(item1.getStudent().getFullName());
                    }
                }
            });
        }
    }
}
