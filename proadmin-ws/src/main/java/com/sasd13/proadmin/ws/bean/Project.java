package com.sasd13.proadmin.ws.bean;

import java.util.Date;

public class Project {

	private String code, title, description;
	private Date dateCreation;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Project other = (Project) obj;

		if (code == null && other.code != null)
			return false;
		else if (!code.equals(other.code))
			return false;

		return true;
	}
}
