package com.sasd13.proadmin.aaa.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.bean.CredentialUpdate;
import com.sasd13.proadmin.itf.bean.user.update.CredentialUpdateBean;

public class CredentialUpdateAdapterI2B implements IAdapter<CredentialUpdateBean, CredentialUpdate> {

	@Override
	public CredentialUpdate adapt(CredentialUpdateBean s) {
		CredentialUpdate t = new CredentialUpdate();

		Credential previous = new Credential();
		previous.setUsername(s.getPrevious().getUsername());
		previous.setPassword(s.getPrevious().getPassword());
		t.setPrevious(previous);

		Credential current = new Credential();
		current.setUsername(s.getCurrent().getUsername());
		current.setPassword(s.getCurrent().getPassword());
		t.setCurrent(current);

		return t;
	}
}
