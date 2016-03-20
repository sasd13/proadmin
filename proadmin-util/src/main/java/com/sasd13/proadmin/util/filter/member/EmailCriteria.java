package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.member.Member;

public class EmailCriteria<T extends Member> implements Criteria<T> {
	
	private String email;
	
	public EmailCriteria(String email) {
		this.email = email;
	}
	
	@Override
	public List<T> meetCriteria(List<T> list) {
		List<T> results = new ArrayList<>();
		
		for (T t : list) {
			if (t.getEmail().equalsIgnoreCase(email)) {
				results.add(t);
			}
		}
		
		return results;
	}
}
