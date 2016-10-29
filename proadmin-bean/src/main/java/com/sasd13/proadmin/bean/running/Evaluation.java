package com.sasd13.proadmin.bean.running;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sasd13.proadmin.bean.member.Student;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Evaluation {

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
