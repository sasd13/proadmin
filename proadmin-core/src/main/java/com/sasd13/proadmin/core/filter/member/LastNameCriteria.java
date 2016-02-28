package com.sasd13.proadmin.core.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.member.AcademicMember;

public class LastNameCriteria<T extends AcademicMember> implements Criteria<T> {
	
	private String lastName;
	
	public LastNameCriteria(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public List<T> meetCriteria(List<T> entities) {
		List<T> result = new ArrayList<>();
		
		for (T t : entities) {
			if (lastName.equalsIgnoreCase(t.getLastName())) {
				result.add(t);
			}
		}
		
		return result;
	}
}
