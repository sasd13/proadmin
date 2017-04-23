package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.member.IStudent;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentFilter extends AndFilter<IStudent> {

	public StudentFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<IStudent> multiAndCriteria, Map<String, String[]> parameters) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.INTERMEDIARY.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberIntermediaryCriteria<IStudent>(value));
				} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberFirstNameCriteria<IStudent>(value));
				} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberLastNameCriteria<IStudent>(value));
				} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberEmailCriteria<IStudent>(value));
				}
			}
		}
	}
}
