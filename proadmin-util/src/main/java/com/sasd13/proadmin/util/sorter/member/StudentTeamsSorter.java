package com.sasd13.proadmin.util.sorter.member;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.member.StudentTeam;

public class StudentTeamsSorter {

	public static void byTeamNumber(List<StudentTeam> studentTeams) {
		byTeamNumber(studentTeams, true);
	}

	public static void byTeamNumber(List<StudentTeam> studentTeams, final boolean byAsc) {
		if (!studentTeams.isEmpty()) {
			Collections.sort(studentTeams, new Comparator<StudentTeam>() {

				@Override
				public int compare(StudentTeam studentTeam1, StudentTeam studentTeam2) {
					if (byAsc) {
						return studentTeam1.getTeam().getNumber().compareTo(studentTeam2.getTeam().getNumber());
					} else {
						return studentTeam2.getTeam().getNumber().compareTo(studentTeam1.getTeam().getNumber());
					}
				}
			});
		}
	}

	public static void byStudentNumber(List<StudentTeam> studentTeams) {
		byStudentNumber(studentTeams, true);
	}

	public static void byStudentNumber(List<StudentTeam> studentTeams, final boolean byAsc) {
		if (!studentTeams.isEmpty()) {
			Collections.sort(studentTeams, new Comparator<StudentTeam>() {

				@Override
				public int compare(StudentTeam studentTeam1, StudentTeam studentTeam2) {
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
