package com.sasd13.proadmin.util.builder.member;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Student;

public class StudentBaseBuilder implements IBuilder<Student> {

	private String number;

	public StudentBaseBuilder(String number) {
		this.number = number;
	}

	@Override
	public Student build() {
		Student student = new Student();
		student.setNumber(number);

		return student;
	}
}
