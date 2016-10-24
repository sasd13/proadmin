package com.sasd13.proadmin.aaa.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.proadmin.aaa.Credential;
import com.sasd13.proadmin.aaa.dao.CredentialDAO;
import com.sasd13.proadmin.aaa.dao.impl.JDBCCredentialDAO;
import com.sasd13.proadmin.aaa.service.ICredentialManageService;

public class CredentialManageService implements ICredentialManageService {

	private static final Logger LOG = Logger.getLogger(CredentialManageService.class);

	private CredentialDAO dao;
	
	public CredentialManageService() {
		dao = new JDBCCredentialDAO();
	}

	@Override
	public boolean createCredential(Credential credential) {
		boolean inserted = false;

		try {
			LOG.info("Inserting credential for user : " + credential.getUsername());

			dao.open();

			inserted = dao.insert(credential);

			LOG.info("Inserted ! ");
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
			LOG.info("Updating credential for user : " + credential.getUsername());

			dao.open();

			updated = dao.update(credential);

			LOG.info("Updated ! ");
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
