package com.sasd13.proadmin.itf.bean.runningteam;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id" })
public class LinkedId {

	private int yearStarted;
	private String projectCode, teacherIntermediary, teamNumber, academicLevelCode;

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

	public String getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getAcademicLevelCode() {
		return academicLevelCode;
	}

	public void setAcademicLevelCode(String academicLevelCode) {
		this.academicLevelCode = academicLevelCode;
	}
}
