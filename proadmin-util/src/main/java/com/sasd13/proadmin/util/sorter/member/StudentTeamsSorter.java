package com.sasd13.proadmin.util.sorter.member;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.member.IStudentTeam;

public class StudentTeamsSorter {

	public static void byTeamNumber(List<IStudentTeam> iStudentTeams) {
		byTeamNumber(iStudentTeams, true);
	}

	public static void byTeamNumber(List<IStudentTeam> iStudentTeams, final boolean byAsc) {
		if (!iStudentTeams.isEmpty()) {
			Collections.sort(iStudentTeams, new Comparator<IStudentTeam>() {

				@Override
				public int compare(IStudentTeam studentTeam1, IStudentTeam studentTeam2) {
					if (byAsc) {
						return studentTeam1.getTeam().getNumber().compareTo(studentTeam2.getTeam().getNumber());
					} else {
						return studentTeam2.getTeam().getNumber().compareTo(studentTeam1.getTeam().getNumber());
					}
				}
			});
		}
	}

	public static void byStudentNumber(List<IStudentTeam> iStudentTeams) {
		byStudentNumber(iStudentTeams, true);
	}

	public static void byStudentNumber(List<IStudentTeam> iStudentTeams, final boolean byAsc) {
		if (!iStudentTeams.isEmpty()) {
			Collections.sort(iStudentTeams, new Comparator<IStudentTeam>() {

				@Override
				public int compare(IStudentTeam studentTeam1, IStudentTeam studentTeam2) {
					if (byAsc) {
						return studentTeam1.getStudent().getIntermediary().compareTo(studentTeam2.getStudent().getIntermediary());
					} else {
						return studentTeam2.getStudent().getIntermediary().compareTo(studentTeam1.getStudent().getIntermediary());
					}
				}
			});
		}
	}
}
