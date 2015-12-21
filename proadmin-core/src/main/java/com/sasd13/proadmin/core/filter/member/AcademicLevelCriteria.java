package com.sasd13.proadmin.core.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;

public class AcademicLevelCriteria implements Criteria<Student> {
	
	private AcademicLevel academicLevel;
	
	public AcademicLevelCriteria(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}
	
	@Override
	public List<Student> meetCriteria(List<Student> entities) {
		List<Student> result = new ArrayList<Student>();
		
		for (Student student : entities) {
			if (academicLevel.equals(student.getAcademicLevel())) {
				result.add(student);
			}
		}
		
		return result;
	}
	
}
