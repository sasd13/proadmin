package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentFilter extends AndFilter<Student> {

	public StudentFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(Map<String, String[]> parameters, MultiAndCriteria<Student> multiAndCriteria) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberNumberCriteria<Student>(value));
				} else if (EnumParameter.FIRSTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberFirstNameCriteria<Student>(value));
				} else if (EnumParameter.LASTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberLastNameCriteria<Student>(value));
				} else if (EnumParameter.EMAIL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new MemberEmailCriteria<Student>(value));
				} else if (EnumParameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new StudentAcademicLevelCriteria(EnumAcademicLevel.find(entry.getKey())));
				}
			}
		}
	}
}
