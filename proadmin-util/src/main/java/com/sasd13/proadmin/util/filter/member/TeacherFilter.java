package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;

public class TeacherFilter extends AndFilter<Teacher> {

	public TeacherFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(Map<String, String[]> parameters, MultiAndCriteria<Teacher> multiAndCriteria) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.NUMBER.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberNumberCriteria<Teacher>(value));
				} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberFirstNameCriteria<Teacher>(value));
				} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberLastNameCriteria<Teacher>(value));
				} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberEmailCriteria<Teacher>(value));
				}
			}
		}
	}
}
