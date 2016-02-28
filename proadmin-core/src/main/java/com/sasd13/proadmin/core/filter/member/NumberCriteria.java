package com.sasd13.proadmin.core.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.member.AcademicMember;

public class NumberCriteria<T extends AcademicMember> implements Criteria<T> {
	
	private String number;
	
	public NumberCriteria(String number) {
		this.number = number;
	}
	
	@Override
	public List<T> meetCriteria(List<T> entities) {
		List<T> result = new ArrayList<>();
		
		for (T t : entities) {
			if (number.equalsIgnoreCase(t.getNumber())) {
				result.add(t);
			}
		}
		
		return result;
	}
}
