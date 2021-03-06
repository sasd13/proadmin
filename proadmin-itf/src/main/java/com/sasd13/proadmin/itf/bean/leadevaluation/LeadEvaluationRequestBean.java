package com.sasd13.proadmin.itf.bean.leadevaluation;

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
public class LeadEvaluationRequestBean extends RequestBean {

	@JsonProperty("data")
	private List<LeadEvaluationBean> data;

	public LeadEvaluationRequestBean() {
		data = new ArrayList<>();
	}

	public List<LeadEvaluationBean> getData() {
		return data;
	}

	public void setData(List<LeadEvaluationBean> data) {
		this.data = data;
	}
}
