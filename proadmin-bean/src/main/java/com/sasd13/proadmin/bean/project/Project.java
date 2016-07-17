package com.sasd13.proadmin.bean.project;

import com.sasd13.proadmin.bean.EnumAcademicLevel;

public class Project {
	
	private long id;
	private EnumAcademicLevel academicLevel;
	private String code, title, description;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public EnumAcademicLevel getAcademicLevel() {
		return academicLevel;
	}
	
	public void setAcademicLevel(EnumAcademicLevel academicLevel) {
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
		
		return builder.toString();
	}
}
