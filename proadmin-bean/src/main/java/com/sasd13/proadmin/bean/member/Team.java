package com.sasd13.proadmin.bean.member;

public class Team {

	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Team [");
		builder.append("number=" + getNumber());
		builder.append("]");

		return builder.toString();
	}
}
