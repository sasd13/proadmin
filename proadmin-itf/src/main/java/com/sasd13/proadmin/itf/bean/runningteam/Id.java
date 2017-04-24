package com.sasd13.proadmin.itf.bean.runningteam;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "linkedId" })
public class Id {

	@JsonProperty("id")
	private String id;

	@JsonProperty("linkedId")
	private LinkedId linkedId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LinkedId getLinkedId() {
		return linkedId;
	}

	public void setLinkedId(LinkedId linkedId) {
		this.linkedId = linkedId;
	}
}
