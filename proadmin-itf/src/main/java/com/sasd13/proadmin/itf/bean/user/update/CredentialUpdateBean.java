package com.sasd13.proadmin.itf.bean.user.update;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.user.LinkedInfo;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id" })
public class CredentialUpdateBean {

	private LinkedInfo previous, current;

	public LinkedInfo getPrevious() {
		return previous;
	}

	public void setPrevious(LinkedInfo previous) {
		this.previous = previous;
	}

	public LinkedInfo getCurrent() {
		return current;
	}

	public void setCurrent(LinkedInfo current) {
		this.current = current;
	}
}
