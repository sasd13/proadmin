package com.sasd13.proadmin.aaa.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.proadmin.aaa.bean.AAAException;
import com.sasd13.proadmin.aaa.bean.Credential;
import com.sasd13.proadmin.aaa.dao.DAOManager;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;

public class CredentialManageService implements ICredentialManageService {

	private static final Logger LOG = Logger.getLogger(CredentialManageService.class);

	private ICredentialDAO dao;

	public CredentialManageService() {
		dao = DAOManager.create();
	}

	@Override
	public boolean createCredential(Credential credential) throws AAAException {
		boolean inserted = false;

		try {
			LOG.info("CredentialManageService --> create : " + credential.getUsername());
			dao.open();

			inserted = dao.insert(credential);
		} catch (DAOException e) {
			LOG.error(e);
			throw new AAAException("CredentialManageService --> create failed");
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return inserted;
	}

	@Override
	public boolean updateCredential(Credential credential) throws AAAException {
		boolean updated = false;

		try {
			LOG.info("CredentialManageService --> update : " + credential.getUsername());
			dao.open();

			updated = dao.update(credential);
		} catch (DAOException e) {
			LOG.error(e);
			throw new AAAException("CredentialManageService --> update failed");
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return updated;
	}

}
