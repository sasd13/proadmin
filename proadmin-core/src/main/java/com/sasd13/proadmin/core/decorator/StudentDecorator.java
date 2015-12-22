package com.sasd13.proadmin.core.decorator;

import com.sasd13.proadmin.core.bean.member.Student;

public class StudentDecorator extends Student {
	
	private Student student;
	private boolean deleted;
	
	public StudentDecorator(Student student) {
		this.student = student;
	}
	
	public Student getStudent() {
		return student;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
