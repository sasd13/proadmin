package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Teacher;

public class TeacherUpdateWrapper implements IUpdateWrapper<Teacher> {

	private Teacher teacher;
	private String intermediary;

	@Override
	public Teacher getWrapped() {
		return teacher;
	}

	@Override
	public void setWrapped(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}
}
