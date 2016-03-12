package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.member.Member;

public class NumberCriteria<T extends Member> implements Criteria<T> {
	
	private String number;
	
	public NumberCriteria(String number) {
		this.number = number;
	}
	
	@Override
	public List<T> meetCriteria(List<T> list) {
		List<T> results = new ArrayList<>();
		
		for (T t : list) {
			if (t.getNumber().equalsIgnoreCase(number)) {
				results.add(t);
			}
		}
		
		return results;
	}
}
