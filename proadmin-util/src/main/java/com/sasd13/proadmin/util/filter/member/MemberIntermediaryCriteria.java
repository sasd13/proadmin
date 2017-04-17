package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.Member;

public class MemberIntermediaryCriteria<T extends Member> implements Criteria<T> {

	private String intermediary;

	public MemberIntermediaryCriteria(String intermediary) {
		this.intermediary = intermediary;
	}

	@Override
	public List<T> meetCriteria(List<T> list) {
		List<T> results = new ArrayList<>();

		for (T t : list) {
			if (t.getIntermediary().equalsIgnoreCase(intermediary)) {
				results.add(t);
			}
		}

		return results;
	}
}
