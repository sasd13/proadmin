package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.Member;

public class MemberFirstNameCriteria<T extends Member> implements Criteria<T> {

	private String firstName;

	public MemberFirstNameCriteria(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public List<T> meetCriteria(List<T> list) {
		List<T> results = new ArrayList<>();

		for (T t : list) {
			if (t.getFirstName().equalsIgnoreCase(firstName)) {
				results.add(t);
			}
		}

		return results;
	}
}
