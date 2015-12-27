package com.sasd13.proadmin.core.bean.running;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sasd13.proadmin.core.bean.member.Student;

public class Team {
	
	private long id;
	private String code;
	private List<Student> students;
	private List<Report> reports;
	private Running running;
	
	public Team() {
		this.reports = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public List<Student> getStudents() {
		return this.students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addReport(Report report) {
		this.reports.add(report);
	}
	
	public void removeReport(Report report) {
		this.reports.remove(report);
	}
	
	public Report[] getReports() {
		return this.reports.toArray(new Report[this.reports.size()]);
	}
	
	public Running getRunning() {
		return this.running;
	}
	
	public void setRunning(Running running) {
		this.running = running;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Team [");
		builder.append("id=" + getId());
		builder.append(", code=" + getCode());
		builder.append(", students=" + Arrays.toString(getStudents().toArray(new Student[getStudents().size()])));
		builder.append(", reports=" + Arrays.toString(getReports()));
		builder.append(", running=" + getRunning());
		builder.append("]");
		
		return builder.toString().trim();
	}
}
