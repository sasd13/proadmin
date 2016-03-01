package com.sasd13.proadmin.core.bean.running;

import com.sasd13.proadmin.core.bean.member.Student;

public abstract class Evaluation {
	
	private long id;
	private Student student;
	private Report report;
	
	protected Evaluation() {}
	
	protected Evaluation(Report report) {
		this.report = report;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Report getReport() {
		return report;
	}
}
