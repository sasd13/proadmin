package com.sasd13.proadmin.itf.bean.individualevaluation;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id" })
public class LinkedId {

	private String reportNumber, studentIntermediary;

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getStudentIntermediary() {
		return studentIntermediary;
	}

	public void setStudentIntermediary(String studentIntermediary) {
		this.studentIntermediary = studentIntermediary;
	}
}
