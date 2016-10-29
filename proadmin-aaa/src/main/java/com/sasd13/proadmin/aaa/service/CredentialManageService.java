package com.sasd13.proadmin.aaa.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.ICredentialManageService;
import com.sasd13.proadmin.aaa.dao.DAOManager;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;

public class CredentialManageService implements ICredentialManageService {

	private static final Logger LOG = Logger.getLogger(CredentialManageService.class);

	private ICredentialDAO dao;

	public CredentialManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void createCredential(Credential credential) throws AAAException {
		LOG.info("CredentialManageService --> createCredential : " + credential.getUsername());

		try {
			dao.open();
			dao.insert(credential);
		} catch (DAOException e) {
			LOG.error("CredentialManageService --> createCredential failed");
			throw new AAAException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void updateCredential(Credential credential) throws AAAException {
		LOG.info("CredentialManageService --> updateCredential : " + credential.getUsername());

		try {
			dao.open();
			dao.update(credential);
		} catch (DAOException e) {
			LOG.error("CredentialManageService --> updateCredential failed");
			throw new AAAException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

}
