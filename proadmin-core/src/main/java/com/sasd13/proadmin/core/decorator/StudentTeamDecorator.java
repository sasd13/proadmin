package com.sasd13.proadmin.core.decorator;

import com.sasd13.proadmin.core.bean.running.StudentTeam;

public class StudentTeamDecorator extends StudentTeam {
	
	private StudentTeam studentTeam;
	private boolean deleted;
	
	public StudentTeamDecorator(StudentTeam studentTeam) {
		this.studentTeam = studentTeam;
	}
	
	public StudentTeam getStudentTeam() {
		return studentTeam;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
