package com.sasd13.proadmin.bean.member;

public class Student extends Member {
	
	public Student() {
		super();
	}
	
	public Student(String number) {
		super(number);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Student [");
		builder.append("number=" + getNumber());
		builder.append(", firstName=" + getFirstName());
		builder.append(", lastName=" + getLastName());
		builder.append(", email=" + getEmail());
		builder.append("]");

		return builder.toString();
	}
}
