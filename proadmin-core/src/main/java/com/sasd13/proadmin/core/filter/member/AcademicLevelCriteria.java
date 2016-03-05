package com.sasd13.proadmin.core.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;

public class AcademicLevelCriteria implements Criteria<Student> {
	
	private AcademicLevel academicLevel;
	
	public AcademicLevelCriteria(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}
	
	@Override
	public List<Student> meetCriteria(List<Student> list) {
		List<Student> results = new ArrayList<Student>();
		
		for (Student student : list) {
			if (student.getAcademicLevel() == academicLevel) {
				results.add(student);
			}
		}
		
		return results;
	}
}
