package com.sasd13.proadmin.itf.bean.individualevaluation;

import com.sasd13.proadmin.itf.bean.Id;

public class IndividualEvaluationBean {

	private Id id;
	private CoreInfo coreInfo;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public CoreInfo getCoreInfo() {
		return coreInfo;
	}

	public void setCoreInfo(CoreInfo coreInfo) {
		this.coreInfo = coreInfo;
	}
}
