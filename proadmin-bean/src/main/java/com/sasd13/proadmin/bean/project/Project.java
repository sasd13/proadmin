package com.sasd13.proadmin.bean.project;

import java.sql.Timestamp;

public class Project {

	private String code, title, description;
	private Timestamp dateCreation;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
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
		builder.append("code=" + getCode());
		builder.append(", dateCreation=" + String.valueOf(getDateCreation()));
		builder.append(", title=" + getTitle());
		builder.append(", description=" + getDescription());
		builder.append("]");

		return builder.toString();
	}
}
