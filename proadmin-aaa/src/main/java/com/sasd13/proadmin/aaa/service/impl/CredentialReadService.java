package com.sasd13.proadmin.aaa.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.proadmin.aaa.Credential;
import com.sasd13.proadmin.aaa.dao.CredentialDAO;
import com.sasd13.proadmin.aaa.dao.impl.JDBCCredentialDAO;
import com.sasd13.proadmin.aaa.service.ICredentialReadService;

public class CredentialReadService implements ICredentialReadService {

	private static final Logger LOG = Logger.getLogger(CredentialReadService.class);

	private CredentialDAO dao;

	public CredentialReadService() {
		dao = new JDBCCredentialDAO();
	}

	@Override
	public boolean containsCredential(Credential credential) {
		boolean contains = false;

		try {
			LOG.info("Checking for user :" + credential.getUsername());

			dao.open();

			contains = dao.contains(credential);

			LOG.info("Checked ! ");
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
