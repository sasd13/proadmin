package com.sasd13.proadmin.bean.member;

public class Team {

	private String number;

	public Team() {
	}

	public Team(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Team [");
		builder.append("number=" + getNumber());
		builder.append("]");

		return builder.toString();
	}
}
