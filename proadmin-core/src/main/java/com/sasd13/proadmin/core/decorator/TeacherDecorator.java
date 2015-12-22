package com.sasd13.proadmin.core.decorator;

import com.sasd13.proadmin.core.bean.member.Teacher;

public class TeacherDecorator extends Teacher {
	
	private Teacher teacher;
	private boolean deleted;
	
	public TeacherDecorator(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
