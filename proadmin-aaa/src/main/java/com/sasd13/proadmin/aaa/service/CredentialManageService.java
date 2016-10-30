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
	public void create(Credential credential) throws AAAException {
		LOG.info("create : username=" + credential.getUsername());

		try {
			dao.open();
			dao.insert(credential);
		} catch (DAOException e) {
			LOG.error("create failed");
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
	public void update(Credential credential) throws AAAException {
		LOG.info("update : username=" + credential.getUsername());

		try {
			dao.open();
			dao.update(credential);
		} catch (DAOException e) {
			LOG.error("update failed");
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
	public void delete(Credential credential) throws ServiceException {
		LOG.info("delete : username=" + credential.getUsername());

		try {
			dao.open();
			dao.delete(credential);
		} catch (DAOException e) {
			LOG.error("delete failed");
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
