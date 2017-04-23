package com.sasd13.proadmin.itf.bean.user.update;

import com.sasd13.proadmin.itf.bean.user.LinkedInfo;

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
