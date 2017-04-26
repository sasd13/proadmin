package com.sasd13.proadmin.itf.bean.studentteam;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.RequestBean;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "data" })
public class StudentTeamRequestBean extends RequestBean {

	@JsonProperty("data")
	private List<StudentTeamBean> data;

	public StudentTeamRequestBean() {
		data = new ArrayList<>();
	}

	public List<StudentTeamBean> getData() {
		return data;
	}

	public void setData(List<StudentTeamBean> data) {
		this.data = data;
	}
}
