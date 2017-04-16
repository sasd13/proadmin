package com.sasd13.proadmin.bean.member;

public class Teacher extends Member {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Teacher [");
		builder.append("number=" + getNumber());
		builder.append(", firstName=" + getFirstName());
		builder.append(", lastName=" + getLastName());
		builder.append(", email=" + getEmail());
		builder.append(", username=" + getUsername());
		builder.append("]");

		return builder.toString();
	}
}
