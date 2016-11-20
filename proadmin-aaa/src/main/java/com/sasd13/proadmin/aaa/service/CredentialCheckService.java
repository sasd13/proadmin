package com.sasd13.proadmin.aaa.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.ICheckService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.aaa.dao.DAOManager;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;

public class CredentialCheckService implements ICheckService<Credential> {

	private static final Logger LOGGER = Logger.getLogger(CredentialCheckService.class);

	private ICredentialDAO dao;

	public CredentialCheckService() {
		dao = DAOManager.create();
	}

	@Override
	public boolean contains(Credential credential) throws ServiceException {
		LOGGER.info("check : username=" + credential.getUsername());

		boolean contains = false;

		try {
			dao.open();

			contains = dao.contains(credential);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}

		return contains;
	}
}
