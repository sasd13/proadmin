package com.sasd13.proadmin.core.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.member.Student;

public class AcademicLevelCriteria implements Criteria<Student> {
	
	private String academicLevel;
	
	public AcademicLevelCriteria(String academicLevel) {
		this.academicLevel = academicLevel;
	}
	
	@Override
	public List<Student> meetCriteria(List<Student> entities) {
		List<Student> result = new ArrayList<Student>();
		
		for (Student student : entities) {
			if (academicLevel.equalsIgnoreCase(String.valueOf(student.getAcademicLevel()))) {
				result.add(student);
			}
		}
		
		return result;
	}
	
}
