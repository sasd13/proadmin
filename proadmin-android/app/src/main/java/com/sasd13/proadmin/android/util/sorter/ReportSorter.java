package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.bean.Report;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReportSorter {

    public static void byNumber(List<Report> list) {
        byNumber(list, true);
    }

    public static void byNumber(List<Report> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<Report>() {

                @Override
                public int compare(Report item1, Report item2) {
                    if (byAsc) {
                        return item1.getNumber().compareTo(item2.getNumber());
                    } else {
                        return item2.getNumber().compareTo(item1.getNumber());
                    }
                }
            });
        }
    }

    public static void bySession(List<Report> list) {
        bySession(list, true);
    }

    public static void bySession(List<Report> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<Report>() {

                @Override
                public int compare(Report item1, Report item2) {
                    if (byAsc) {
                        return Integer.compare(item1.getSession(), item2.getSession());
                    } else {
                        return Integer.compare(item2.getSession(), item1.getSession());
                    }
                }
            });
        }
    }

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
