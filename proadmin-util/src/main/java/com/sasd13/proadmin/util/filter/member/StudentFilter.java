package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.FilterException;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentFilter extends AndFilter<Student> {

	public StudentFilter(Map<String, String[]> parameters) throws FilterException {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<Student> multiAndCriteria, Map<String, String[]> parameters) throws FilterException {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.NUMBER.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberNumberCriteria<Student>(value));
				} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberFirstNameCriteria<Student>(value));
				} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberLastNameCriteria<Student>(value));
				} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberEmailCriteria<Student>(value));
				}
			}
		}
	}
}
