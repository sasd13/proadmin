/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.core.bean.running;

import com.sasd13.proadmin.core.bean.member.Student;

/**
 *
 * @author Samir
 */
public abstract class Evaluation {
	
	private long id;
	private Student student;
	private Report report;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Student getStudent() {
		return this.student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Report getReport() {
		return this.report;
	}
	
	public void setReport(Report report) {
		this.report = report;
	}
}
