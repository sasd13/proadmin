package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.StudentTeam;

public class StudentTeamStudentCriteria implements Criteria<StudentTeam> {

	private String intermediary;

	public StudentTeamStudentCriteria(String intermediary) {
		this.intermediary = intermediary;
	}

	@Override
	public List<StudentTeam> meetCriteria(List<StudentTeam> list) {
		List<StudentTeam> results = new ArrayList<>();

		for (StudentTeam studentTeam : list) {
			if (studentTeam.getStudent().getIntermediary().equalsIgnoreCase(intermediary)) {
				results.add(studentTeam);
			}
		}

		return results;
	}
}
