package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.criteria.AndFilter;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentFilter extends AndFilter<Student> {
	
	public StudentFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new NumberCriteria<Student>(value));
				} else if (EnumParameter.FIRSTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new FirstNameCriteria<Student>(value));
				} else if (EnumParameter.LASTNAME.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new LastNameCriteria<Student>(value));
				} else if (EnumParameter.EMAIL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new EmailCriteria<Student>(value));
				} else if (EnumParameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(EnumAcademicLevel.find(entry.getKey())));
				} 
			}
		}
	}
}
