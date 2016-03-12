package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.criteria.AndFilter;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.Parameter;

public class TeacherFilter extends AndFilter<Teacher> {
	
	public TeacherFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new NumberCriteria<Teacher>(value));
				} else if (Parameter.FIRSTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new FirstNameCriteria<Teacher>(value));
				} else if (Parameter.LASTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new LastNameCriteria<Teacher>(value));
				} else if (Parameter.EMAIL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new EmailCriteria<Teacher>(value));
				}
			}
		}
	}
}
