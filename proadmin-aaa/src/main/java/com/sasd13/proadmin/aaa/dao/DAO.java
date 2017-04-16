package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.IDAO;

public abstract class DAO implements IDAO {

	protected ICredentialDAO credentialDAO;
	protected IProfileDAO profileDAO;

	protected DAO(ICredentialDAO credentialDAO, IProfileDAO profileDAO) {
		this.credentialDAO = credentialDAO;
		this.profileDAO = profileDAO;
	}

	public ICredentialDAO getCredentialDAO() {
		return credentialDAO;
	}

	public IProfileDAO getProfileDAO() {
		return profileDAO;
	}
}
