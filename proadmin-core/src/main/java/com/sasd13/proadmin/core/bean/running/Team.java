package com.sasd13.proadmin.core.bean.running;

import java.util.ArrayList;
import java.util.List;

public class Team {
	
	private long id;
	private String code;
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
	
	public List<Report> getReports() {
		return reports;
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
