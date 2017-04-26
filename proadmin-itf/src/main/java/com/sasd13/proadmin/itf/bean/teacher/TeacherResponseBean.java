package com.sasd13.proadmin.itf.bean.teacher;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.ResponseBean;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "data" })
public class TeacherResponseBean extends ResponseBean {

	@JsonProperty("data")
	private List<TeacherBean> data;

	public TeacherResponseBean() {
		data = new ArrayList<>();
	}

	public List<TeacherBean> getData() {
		return data;
	}

	public void setData(List<TeacherBean> data) {
		this.data = data;
	}
}
