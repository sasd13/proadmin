package com.sasd13.proadmin.itf.bean.running;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id" })
public class LinkedId {

	private int yearStarted;
	private String projectCode, teacherIntermediary;

	public int getYearStarted() {
		return yearStarted;
	}

	public void setYearStarted(int yearStarted) {
		this.yearStarted = yearStarted;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getTeacherIntermediary() {
		return teacherIntermediary;
	}

	public void setTeacherIntermediary(String teacherIntermediary) {
		this.teacherIntermediary = teacherIntermediary;
	}
}
