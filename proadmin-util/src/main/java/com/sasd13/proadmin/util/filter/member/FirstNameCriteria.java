package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.member.Member;

public class FirstNameCriteria<T extends Member> implements Criteria<T> {
	
	private String firstName;
	
	public FirstNameCriteria(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public List<T> meetCriteria(List<T> list) {
		List<T> results = new ArrayList<>();
		
		for (T t : list) {
			if (firstName.equalsIgnoreCase(t.getFirstName())) {
				results.add(t);
			}
		}
		
		return results;
	}
}
