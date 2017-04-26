package com.sasd13.proadmin.itf.bean.team;

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
public class TeamResponseBean extends ResponseBean {

	@JsonProperty("data")
	private List<TeamBean> data;

	public TeamResponseBean() {
		data = new ArrayList<>();
	}

	public List<TeamBean> getData() {
		return data;
	}

	public void setData(List<TeamBean> data) {
		this.data = data;
	}
}
