package com.sasd13.proadmin.bean.running;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sasd13.proadmin.bean.member.Student;

public abstract class Evaluation {

	@JsonManagedReference("report")
	private Report report;

	private Student student;

	protected Evaluation(Report report) {
		this.report = report;
	}

	public Report getReport() {
		return report;
	}

	void setReport(Report report) {
		this.report = report;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
