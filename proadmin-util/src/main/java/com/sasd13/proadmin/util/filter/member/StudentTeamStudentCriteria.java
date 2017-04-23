package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.IStudentTeam;

public class StudentTeamStudentCriteria implements Criteria<IStudentTeam> {

	private String intermediary;

	public StudentTeamStudentCriteria(String intermediary) {
		this.intermediary = intermediary;
	}

	@Override
	public List<IStudentTeam> meetCriteria(List<IStudentTeam> list) {
		List<IStudentTeam> results = new ArrayList<>();

		for (IStudentTeam item : list) {
			if (item.getStudent().getIntermediary().equalsIgnoreCase(intermediary)) {
				results.add(item);
			}
		}

		return results;
	}
}
