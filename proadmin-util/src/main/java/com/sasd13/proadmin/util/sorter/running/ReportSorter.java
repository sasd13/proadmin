package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.IReport;

public class ReportSorter {

	public static void byNumber(List<IReport> list) {
		byNumber(list, true);
	}

	public static void byNumber(List<IReport> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IReport>() {

				@Override
				public int compare(IReport item1, IReport item2) {
					if (byAsc) {
						return item1.getNumber().compareTo(item2.getNumber());
					} else {
						return item2.getNumber().compareTo(item1.getNumber());
					}
				}
			});
		}
	}

	public static void bySession(List<IReport> list) {
		bySession(list, true);
	}

	public static void bySession(List<IReport> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IReport>() {

				@Override
				public int compare(IReport item1, IReport item2) {
					if (byAsc) {
						return Integer.compare(item1.getSession(), item2.getSession());
					} else {
						return Integer.compare(item2.getSession(), item1.getSession());
					}
				}
			});
		}
	}

	public static void byDateMeeting(List<IReport> list) {
		byDateMeeting(list, true);
	}

	public static void byDateMeeting(List<IReport> list, final boolean byDesc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IReport>() {

				@Override
				public int compare(IReport item1, IReport item2) {
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
