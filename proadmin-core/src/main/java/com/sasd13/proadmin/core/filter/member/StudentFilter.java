package com.sasd13.proadmin.core.filter.member;

import java.util.Map;

import com.sasd13.javaex.util.ParameterFilter;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.util.Parameter;

public class StudentFilter extends ParameterFilter<Student> {
	
	public StudentFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new NumberCriteria<Student>(value));
				} else if (Parameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(value));
				} else if (Parameter.FIRSTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new FirstNameCriteria<Student>(value));
				} else if (Parameter.LASTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new LastNameCriteria<Student>(value));
				} else if (Parameter.EMAIL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new EmailCriteria<Student>(value));
				}
			}
		}
	}
}
