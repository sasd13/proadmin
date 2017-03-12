package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Student;

public class StudentUpdateWrapper implements IUpdateWrapper<Student> {

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
