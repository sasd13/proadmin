package com.sasd13.proadmin.aaa.util.adapter.b2m;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.model.CredentialUpdate;
import com.sasd13.proadmin.itf.bean.user.CredentialUpdateBean;

public class CredentialUpdateBeanToCredentialUpdateAdapter implements IAdapter<CredentialUpdateBean, CredentialUpdate> {

	@Override
	public CredentialUpdate adapt(CredentialUpdateBean source) {
		CredentialUpdate target = new CredentialUpdate();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(CredentialUpdateBean source, CredentialUpdate target) {
		target.setOldCredential(source.getOldCredential());
		target.setNewCredential(source.getNewCredential());
	}
}
