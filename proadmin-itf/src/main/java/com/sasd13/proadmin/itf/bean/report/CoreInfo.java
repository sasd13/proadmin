package com.sasd13.proadmin.itf.bean.report;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "number", "dateMeeting", "session", "comment" })
public class CoreInfo {

	@JsonProperty("number")
	private String number;

	@JsonProperty("dateMeeting")
	private String dateMeeting;

	@JsonProperty("session")
	private int session;

	@JsonProperty("comment")
	private String comment;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDateMeeting() {
		return dateMeeting;
	}

	public void setDateMeeting(String dateMeeting) {
		this.dateMeeting = dateMeeting;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
