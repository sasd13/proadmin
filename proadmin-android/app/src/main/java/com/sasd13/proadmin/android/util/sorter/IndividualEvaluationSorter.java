package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.bean.IndividualEvaluation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IndividualEvaluationSorter {

    public static void byStudentIntermediary(List<IndividualEvaluation> list) {
        byStudentIntermediary(list, true);
    }

    public static void byStudentIntermediary(List<IndividualEvaluation> list, final boolean byAsc) {
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
}
