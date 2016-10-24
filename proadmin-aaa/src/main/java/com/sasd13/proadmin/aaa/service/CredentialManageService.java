package com.sasd13.proadmin.aaa.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;
import com.sasd13.proadmin.aaa.dao.JDBCCredentialDAO;
import com.sasd13.proadmin.aaa.entity.Credential;

public class CredentialManageService implements ICredentialManageService {

	private static final Logger LOG = Logger.getLogger(CredentialManageService.class);

	private ICredentialDAO dao;

	public CredentialManageService() {
		dao = new JDBCCredentialDAO();
	}

	@Override
	public boolean createCredential(Credential credential) {
		boolean inserted = false;

		try {
			LOG.info("Insert credential for user : " + credential.getUsername());

			dao.open();

			inserted = dao.insert(credential);
		} catch (DAOException e) {
			LOG.error("Insert failed", e);
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}

		return inserted;
	}

	@Override
	public boolean updateCredential(Credential credential) {
		boolean updated = false;

		try {
			LOG.info("Update credential for user : " + credential.getUsername());

			dao.open();

			updated = dao.update(credential);
		} catch (DAOException e) {
			LOG.error("Update failed", e);
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}

		return updated;
	}

}
