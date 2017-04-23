package com.sasd13.proadmin.util.sorter.member;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.member.IStudentTeam;

public class StudentTeamSorter {

	public static void byTeamNumber(List<IStudentTeam> list) {
		byTeamNumber(list, true);
	}

	public static void byTeamNumber(List<IStudentTeam> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IStudentTeam>() {

				@Override
				public int compare(IStudentTeam item1, IStudentTeam item2) {
					if (byAsc) {
						return item1.getTeam().getNumber().compareTo(item2.getTeam().getNumber());
					} else {
						return item2.getTeam().getNumber().compareTo(item1.getTeam().getNumber());
					}
				}
			});
		}
	}

	public static void byStudentNumber(List<IStudentTeam> list) {
		byStudentNumber(list, true);
	}

	public static void byStudentNumber(List<IStudentTeam> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IStudentTeam>() {

				@Override
				public int compare(IStudentTeam item1, IStudentTeam item2) {
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
