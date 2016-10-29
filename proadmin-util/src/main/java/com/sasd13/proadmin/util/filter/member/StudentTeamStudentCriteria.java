package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.StudentTeam;

public class StudentTeamStudentCriteria implements Criteria<StudentTeam> {

	private String number;

	public StudentTeamStudentCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<StudentTeam> meetCriteria(List<StudentTeam> list) {
		List<StudentTeam> results = new ArrayList<>();

		for (StudentTeam studentTeam : list) {
			if (number.equalsIgnoreCase(studentTeam.getStudent().getNumber())) {
				results.add(studentTeam);
			}
		}

		return results;
	}
}
