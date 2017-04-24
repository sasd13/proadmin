package com.sasd13.proadmin.itf.bean.runningteam;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Id extends com.sasd13.proadmin.itf.bean.Id {

	@JsonInclude(Include.NON_NULL)
	private LinkedId linkedId;

	public LinkedId getLinkedId() {
		return linkedId;
	}

	public void setLinkedId(LinkedId linkedId) {
		this.linkedId = linkedId;
	}
}
