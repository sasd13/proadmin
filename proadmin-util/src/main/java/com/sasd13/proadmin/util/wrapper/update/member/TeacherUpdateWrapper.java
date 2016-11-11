package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.proadmin.bean.member.Teacher;

public class TeacherUpdateWrapper implements ITeacherUpdateWrapper {

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

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public void setNumber(String number) {
		this.number = number;
	}
}
