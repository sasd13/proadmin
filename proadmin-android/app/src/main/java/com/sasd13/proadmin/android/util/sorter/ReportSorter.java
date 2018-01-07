package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.model.Report;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReportSorter {

    public static void byDateMeeting(List<Report> list) {
        byDateMeeting(list, true);
    }

    public static void byDateMeeting(List<Report> list, final boolean byDesc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<Report>() {

                @Override
                public int compare(Report item1, Report item2) {
                    if (!byDesc) {
                        return item1.getDateMeeting().compareTo(item2.getDateMeeting());
                    } else {
                        return item2.getDateMeeting().compareTo(item1.getDateMeeting());
                    }
                }
            });
        }
    }
}
