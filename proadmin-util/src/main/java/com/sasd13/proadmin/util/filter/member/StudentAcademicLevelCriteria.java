package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.member.Student;

public class StudentAcademicLevelCriteria implements Criteria<Student> {

	private EnumAcademicLevel academicLevel;

	public StudentAcademicLevelCriteria(EnumAcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}

	@Override
	public List<Student> meetCriteria(List<Student> list) {
		List<Student> results = new ArrayList<Student>();

		for (Student student : list) {
			if (academicLevel == student.getAcademicLevel()) {
				results.add(student);
			}
		}

		return results;
	}
}
