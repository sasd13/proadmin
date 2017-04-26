package com.sasd13.proadmin.itf.bean.student;

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
public class StudentRequestBean extends RequestBean {

	@JsonProperty("data")
	private List<StudentBean> data;

	public StudentRequestBean() {
		data = new ArrayList<>();
	}

	public List<StudentBean> getData() {
		return data;
	}

	public void setData(List<StudentBean> data) {
		this.data = data;
	}
}
