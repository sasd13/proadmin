package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.Member;

public class MemberLastNameCriteria<T extends Member> implements Criteria<T> {

	private String lastName;

	public MemberLastNameCriteria(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public List<T> meetCriteria(List<T> list) {
		List<T> results = new ArrayList<>();

		for (T t : list) {
			if (lastName.equalsIgnoreCase(t.getLastName())) {
				results.add(t);
			}
		}

		return results;
	}
}