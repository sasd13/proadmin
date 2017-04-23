package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.IReport;

public class ReportsSorter {

	public static void byNumber(List<IReport> iReports) {
		byNumber(iReports, true);
	}

	public static void byNumber(List<IReport> iReports, final boolean byAsc) {
		if (!iReports.isEmpty()) {
			Collections.sort(iReports, new Comparator<IReport>() {

				@Override
				public int compare(IReport report1, IReport report2) {
					if (byAsc) {
						return report1.getNumber().compareTo(report2.getNumber());
					} else {
						return report2.getNumber().compareTo(report1.getNumber());
					}
				}
			});
		}
	}

	public static void bySession(List<IReport> iReports) {
		bySession(iReports, true);
	}

	public static void bySession(List<IReport> iReports, final boolean byAsc) {
		if (!iReports.isEmpty()) {
			Collections.sort(iReports, new Comparator<IReport>() {

				@Override
				public int compare(IReport report1, IReport report2) {
					if (byAsc) {
						return Integer.compare(report1.getSession(), report2.getSession());
					} else {
						return Integer.compare(report2.getSession(), report1.getSession());
					}
				}
			});
		}
	}

	public static void byDateMeeting(List<IReport> iReports) {
		byDateMeeting(iReports, true);
	}

	public static void byDateMeeting(List<IReport> iReports, final boolean byDesc) {
		if (!iReports.isEmpty()) {
			Collections.sort(iReports, new Comparator<IReport>() {

				@Override
				public int compare(IReport report1, IReport report2) {
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
