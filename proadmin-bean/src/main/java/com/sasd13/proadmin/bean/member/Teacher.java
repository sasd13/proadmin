package com.sasd13.proadmin.bean.member;

public class Teacher extends Member {
	
	private String password;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Teacher [");
		builder.append("id=" + getId());
		builder.append(", number=" + getNumber());
		builder.append(", firstName=" + getFirstName());
		builder.append(", lastName=" + getLastName());
		builder.append(", email=" + getEmail());
		builder.append("]");
		
		return builder.toString().trim();
	}
}
