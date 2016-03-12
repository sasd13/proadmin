package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.criteria.AndFilter;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.Parameter;

public class StudentFilter extends AndFilter<Student> {
	
	public StudentFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new NumberCriteria<Student>(value));
				} else if (Parameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new AcademicLevelCriteria(AcademicLevel.valueOf(value)));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
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
