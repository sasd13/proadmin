package com.sasd13.proadmin.bean.running;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sasd13.proadmin.bean.member.Student;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public abstract class Evaluation {

	private Report report;
	private Student student;

	public Evaluation() {
	}

	public Evaluation(String reportNumber, String studentNumber) {
		this.report = new Report(reportNumber);
		this.student = new Student(studentNumber);
	}

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
