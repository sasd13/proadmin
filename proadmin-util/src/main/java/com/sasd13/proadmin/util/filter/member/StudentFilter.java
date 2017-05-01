package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.member.IStudent;
import com.sasd13.proadmin.util.EnumCriteria;

public class StudentFilter extends AndFilter<IStudent> {

	public StudentFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<IStudent> multiAndCriteria, Map<String, String[]> parameters) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberIntermediaryCriteria<IStudent>(value));
				} else if (EnumCriteria.FIRSTNAME.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberFirstNameCriteria<IStudent>(value));
				} else if (EnumCriteria.LASTNAME.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberLastNameCriteria<IStudent>(value));
				} else if (EnumCriteria.EMAIL.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberEmailCriteria<IStudent>(value));
				}
			}
		}
	}
}
