package com.sasd13.proadmin.itf.bean.report;

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
public class ReportResponseBean extends ResponseBean {

	@JsonProperty("data")
	private List<ReportBean> data;

	public ReportResponseBean() {
		data = new ArrayList<>();
	}

	public List<ReportBean> getData() {
		return data;
	}

	public void setData(List<ReportBean> data) {
		this.data = data;
	}
}
