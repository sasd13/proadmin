package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.member.StudentTeam;

public class StudentCriteria implements Criteria<StudentTeam> {
	
	private long id;
	
	public StudentCriteria(long id) {
		this.id = id;
	}
	
	@Override
	public List<StudentTeam> meetCriteria(List<StudentTeam> list) {
		List<StudentTeam> results = new ArrayList<>();
		
		for (StudentTeam studentTeam : list) {
			if (id == studentTeam.getStudent().getId()) {
				results.add(studentTeam);
			}
		}
		
		return results;
	}
}
