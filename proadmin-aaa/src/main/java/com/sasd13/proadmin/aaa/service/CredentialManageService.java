package com.sasd13.proadmin.aaa.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.aaa.dao.DAOManager;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;

public class CredentialManageService implements IManageService<Credential> {

	private static final Logger LOG = Logger.getLogger(CredentialManageService.class);

	private ICredentialDAO dao;

	public CredentialManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Credential[] credentials) throws ServiceException {
		try {
			dao.open();

			for (Credential credential : credentials) {
				LOG.info("create : username=" + credential.getUsername());
				dao.insert(credential);
			}
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(Credential[] credentials) throws ServiceException {
		try {
			dao.open();

			for (Credential credential : credentials) {
				LOG.info("update : username=" + credential.getUsername());
				dao.update(credential);
			}
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void delete(Credential[] credentials) throws ServiceException {
		try {
			dao.open();

			for (Credential credential : credentials) {
				LOG.info("delete : username=" + credential.getUsername());
				dao.delete(credential);
			}
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}
}
