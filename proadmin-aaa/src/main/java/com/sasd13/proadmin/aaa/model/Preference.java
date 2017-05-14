package com.sasd13.proadmin.aaa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "preferences")
public class Preference implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7656666136129268653L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
	private long id;

	@Column(name = "_category")
	private String category;

	@Column(name = "_name")
	private String name;

	@Column(name = "_defaultvalue")
	private String defaultValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Preference other = (Preference) obj;

		if (category == null && other.category != null)
			return false;
		else if (!category.equals(other.category))
			return false;

		if (name == null && other.name != null)
			return false;
		else if (!name.equals(other.name))
			return false;

		return true;
	}
}
