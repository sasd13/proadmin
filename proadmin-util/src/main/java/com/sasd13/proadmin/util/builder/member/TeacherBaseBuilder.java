package com.sasd13.proadmin.util.builder.member;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Teacher;

public class TeacherBaseBuilder implements IBuilder<Teacher> {

	private String number;

	public TeacherBaseBuilder(String number) {
		this.number = number;
	}

	@Override
	public Teacher build() {
		Teacher teacher = new Teacher();
		teacher.setNumber(number);

		return teacher;
	}
}
