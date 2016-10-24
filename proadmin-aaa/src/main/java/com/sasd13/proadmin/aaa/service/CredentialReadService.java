package com.sasd13.proadmin.aaa.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;
import com.sasd13.proadmin.aaa.dao.JDBCCredentialDAO;
import com.sasd13.proadmin.aaa.entity.Credential;

public class CredentialReadService implements ICredentialReadService {

	private static final Logger LOG = Logger.getLogger(CredentialReadService.class);

	private ICredentialDAO dao;

	public CredentialReadService() {
		dao = new JDBCCredentialDAO();
	}

	@Override
	public boolean containsCredential(Credential credential) {
		boolean contains = false;

		try {
			LOG.info("Check credential for user : " + credential.getUsername());

			dao.open();

			contains = dao.contains(credential);
		} catch (DAOException e) {
			LOG.error("Check failed", e);
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}

		return contains;
	}
}
