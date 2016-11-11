package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.proadmin.bean.member.Student;

public class StudentUpdateWrapper implements IStudentUpdateWrapper {

	private Student student;
	private String number;

	@Override
	public Student getWrapped() {
		return student;
	}

	@Override
	public void setWrapped(Student student) {
		this.student = student;
	}

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public void setNumber(String number) {
		this.number = number;
	}
}
