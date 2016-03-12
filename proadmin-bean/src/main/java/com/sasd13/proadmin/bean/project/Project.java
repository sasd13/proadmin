package com.sasd13.proadmin.bean.project;

import com.sasd13.proadmin.bean.AcademicLevel;

public class Project {
	
	private long id;
	private AcademicLevel academicLevel;
	private String code, title, description;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public AcademicLevel getAcademicLevel() {
		return academicLevel;
	}
	
	public void setAcademicLevel(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Project [");
		builder.append("id=" + getId());
		builder.append(", academicLevel=" + getAcademicLevel());
		builder.append(", code=" + getCode());
		builder.append(", title=" + getTitle());
		builder.append(", description=" + getDescription());
		builder.append("]");
		
		return builder.toString().trim();
	}
}
