/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.core.bean.project;

import com.sasd13.proadmin.core.bean.AcademicLevel;

/**
 *
 * @author Samir
 */
public class Project {
	
	private long id;
	private String code, title, description;
	private AcademicLevel academicLevel;
	
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
	
	public AcademicLevel getAcademicLevel() {
		return academicLevel;
	}
	
	public void setAcademicLevel(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
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
		builder.append(", code=" + getCode());
		builder.append(", academicLevel=" + getAcademicLevel());
		builder.append(", title=" + getTitle());
		builder.append(", description=" + getDescription());
		builder.append("]");
		
		return builder.toString();
	}
}
