package com.sasd13.proadmin.aaa.util.adapter.bean2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.model.CredentialUpdate;
import com.sasd13.proadmin.itf.bean.user.CredentialUpdateBean;

public class CredentialUpdateAdapterB2M implements IAdapter<CredentialUpdateBean, CredentialUpdate> {

	@Override
	public CredentialUpdate adapt(CredentialUpdateBean s) {
		CredentialUpdate t = new CredentialUpdate();

		t.setOldCredential(s.getOldCredential());
		t.setNewCredential(s.getNewCredential());

		return t;
	}
}
