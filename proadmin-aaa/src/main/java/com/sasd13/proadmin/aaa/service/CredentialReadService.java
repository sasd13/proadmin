package com.sasd13.proadmin.aaa.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.proadmin.aaa.AAAException;
import com.sasd13.proadmin.aaa.bean.Credential;
import com.sasd13.proadmin.aaa.dao.DAOManager;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;

public class CredentialReadService implements ICredentialReadService {

	private static final Logger LOG = Logger.getLogger(CredentialReadService.class);

	private ICredentialDAO dao;

	public CredentialReadService() {
		dao = DAOManager.create();
	}

	@Override
	public boolean containsCredential(Credential credential) throws AAAException {
		LOG.info("CredentialReadService --> containsCredential : " + credential.getUsername());

		boolean contains = false;

		try {
			dao.open();

			contains = dao.contains(credential);
		} catch (DAOException e) {
			LOG.error("CredentialReadService --> containsCredential failed");
			throw new AAAException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return contains;
	}
}
