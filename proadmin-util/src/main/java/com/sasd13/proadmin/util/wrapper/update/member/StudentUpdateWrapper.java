package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Student;

public class StudentUpdateWrapper implements IUpdateWrapper<Student> {

	private Student student;
	private String intermediary;

	@Override
	public Student getWrapped() {
		return student;
	}

	@Override
	public void setWrapped(Student student) {
		this.student = student;
	}

	public String getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}
}
