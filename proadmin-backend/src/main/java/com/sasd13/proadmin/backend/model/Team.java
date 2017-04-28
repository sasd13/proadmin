package com.sasd13.proadmin.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1738292554927147241L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id")
	private long id;

	@Column(name = "_code")
	private String number;

	@Column(name = "_name")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((number == null) ? 0 : number.hashCode());

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

		Team other = (Team) obj;

		if (number == null && other.number != null)
			return false;
		else if (!number.equals(other.number))
			return false;

		return true;
	}
}
