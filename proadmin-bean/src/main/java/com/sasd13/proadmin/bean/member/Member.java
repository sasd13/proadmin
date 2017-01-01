package com.sasd13.proadmin.bean.member;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Member {

	private String number, firstName, lastName, email;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getFullName() {
		return firstName + " " + lastName;
	}
}
