package com.sasd13.proadmin.aaa.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.ICredentialReadService;
import com.sasd13.proadmin.aaa.dao.DAOManager;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;

public class CredentialReadService implements ICredentialReadService {

	private static final Logger LOG = Logger.getLogger(CredentialReadService.class);

	private ICredentialDAO dao;

	public CredentialReadService() {
		dao = DAOManager.create();
	}

	@Override
	public boolean contains(Credential credential) throws AAAException {
		LOG.info("contains : username=" + credential.getUsername());

		boolean contains = false;

		try {
			dao.open();

			contains = dao.contains(credential);
		} catch (DAOException e) {
			LOG.error("contains failed");
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
