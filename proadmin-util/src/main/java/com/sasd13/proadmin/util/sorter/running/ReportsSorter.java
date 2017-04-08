package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.Report;

public class ReportsSorter {

	public static void byNumber(List<Report> reports) {
		byNumber(reports, true);
	}

	public static void byNumber(List<Report> reports, final boolean byAsc) {
		if (!reports.isEmpty()) {
			Collections.sort(reports, new Comparator<Report>() {

				@Override
				public int compare(Report report1, Report report2) {
					if (byAsc) {
						return report1.getNumber().compareTo(report2.getNumber());
					} else {
						return report2.getNumber().compareTo(report1.getNumber());
					}
				}
			});
		}
	}

	public static void bySession(List<Report> reports) {
		bySession(reports, true);
	}

	public static void bySession(List<Report> reports, final boolean byAsc) {
		if (!reports.isEmpty()) {
			Collections.sort(reports, new Comparator<Report>() {

				@Override
				public int compare(Report report1, Report report2) {
					if (byAsc) {
						return Integer.compare(report1.getSession(), report2.getSession());
					} else {
						return Integer.compare(report2.getSession(), report1.getSession());
					}
				}
			});
		}
	}

	public static void byDateMeeting(List<Report> reports) {
		byDateMeeting(reports, true);
	}

	public static void byDateMeeting(List<Report> reports, final boolean byDesc) {
		if (!reports.isEmpty()) {
			Collections.sort(reports, new Comparator<Report>() {

				@Override
				public int compare(Report report1, Report report2) {
					if (!byDesc) {
						return report1.getDateMeeting().compareTo(report2.getDateMeeting());
					} else {
						return report2.getDateMeeting().compareTo(report1.getDateMeeting());
					}
				}
			});
		}
	}
}
