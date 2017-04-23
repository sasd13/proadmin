package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.IStudentTeam;

public class StudentTeamTeamCriteria implements Criteria<IStudentTeam> {

	private String number;

	public StudentTeamTeamCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<IStudentTeam> meetCriteria(List<IStudentTeam> list) {
		List<IStudentTeam> results = new ArrayList<>();

		for (IStudentTeam iStudentTeam : list) {
			if (iStudentTeam.getTeam().getNumber().equalsIgnoreCase(number)) {
				results.add(iStudentTeam);
			}
		}

		return results;
	}
}
