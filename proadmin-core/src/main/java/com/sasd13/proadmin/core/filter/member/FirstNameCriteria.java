package com.sasd13.proadmin.core.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.member.AcademicMember;

public class FirstNameCriteria<T extends AcademicMember> implements Criteria<T> {
	
	private String firstName;
	
	public FirstNameCriteria(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public List<T> meetCriteria(List<T> entities) {
		List<T> result = new ArrayList<>();
		
		for (T t : entities) {
			if (firstName.equalsIgnoreCase(t.getFirstName())) {
				result.add(t);
			}
		}
		
		return result;
	}
}
