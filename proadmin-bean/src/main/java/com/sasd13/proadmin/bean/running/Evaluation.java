package com.sasd13.proadmin.bean.running;

import com.sasd13.proadmin.bean.member.Student;

public abstract class Evaluation {

	private Report report;
	private Student student;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
