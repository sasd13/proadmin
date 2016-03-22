package com.sasd13.proadmin.bean.member;

public class Team {
	
	private long id;
	private String code;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Team [");
		builder.append("id=" + getId());
		builder.append(", code=" + getCode());
		builder.append("]");
		
		return builder.toString();
	}
}
