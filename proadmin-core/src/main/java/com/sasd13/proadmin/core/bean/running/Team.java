package com.sasd13.proadmin.core.bean.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.proadmin.core.bean.member.Student;

public class Team {
	
	private long id;
	private String code;
	private List<Student> students;
	private List<Report> reports;
	private Running running;
	
	public Team() {
		reports = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addReport(Report report) {
		reports.add(report);
	}
	
	public void removeReport(Report report) {
		reports.remove(report);
	}
	
	public Report[] getReports() {
		return reports.toArray(new Report[reports.size()]);
	}
	
	public Running getRunning() {
		return running;
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
		builder.append("]");
		
		return builder.toString().trim();
	}
}
