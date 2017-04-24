package com.sasd13.proadmin.itf.bean.individualevaluation;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id" })
public class Id extends com.sasd13.proadmin.itf.bean.Id {

	private LinkedId linkedId;

	public LinkedId getLinkedId() {
		return linkedId;
	}

	public void setLinkedId(LinkedId linkedId) {
		this.linkedId = linkedId;
	}
}
