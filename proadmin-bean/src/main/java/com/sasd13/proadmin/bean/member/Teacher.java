package com.sasd13.proadmin.bean.member;

public class Teacher extends Member {
	
	public Teacher() {
		super();
	}
	
	public Teacher(String number) {
		super(number);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Teacher [");
		builder.append("number=" + getNumber());
		builder.append(", firstName=" + getFirstName());
		builder.append(", lastName=" + getLastName());
		builder.append(", email=" + getEmail());
		builder.append("]");

		return builder.toString();
	}
}
