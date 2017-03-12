package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Teacher;

public class TeacherUpdateWrapper implements IUpdateWrapper<Teacher> {

	private Teacher teacher;
	private String number;

	@Override
	public Teacher getWrapped() {
		return teacher;
	}

	@Override
	public void setWrapped(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
