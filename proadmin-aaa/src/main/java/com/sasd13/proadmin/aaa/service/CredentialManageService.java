package com.sasd13.proadmin.aaa.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.aaa.dao.DAOManager;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;
import com.sasd13.proadmin.util.wrapper.update.credential.ICredentialUpdateWrapper;

public class CredentialManageService implements IManageService<Credential> {

	private static final Logger LOGGER = Logger.getLogger(CredentialManageService.class);

	private ICredentialDAO dao;

	public CredentialManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Credential> credentials) throws ServiceException {
		try {
			dao.open();

			for (Credential credential : credentials) {
				LOGGER.info("create : username=" + credential.getUsername());
				dao.insert(credential);
			}
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
	}

	@Override
	public void update(List<IUpdateWrapper<Credential>> updateWrappers) throws ServiceException {
		try {
			dao.open();

			ICredentialUpdateWrapper credentialUpdateWrapper;

			for (IUpdateWrapper<Credential> updateWrapper : updateWrappers) {
				credentialUpdateWrapper = (ICredentialUpdateWrapper) updateWrapper;

				LOGGER.info("update : username=" + credentialUpdateWrapper.getUsername());
				dao.update(credentialUpdateWrapper);
			}
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
	}

	@Override
	public void delete(List<Credential> credentials) throws ServiceException {
		try {
			dao.open();

			for (Credential credential : credentials) {
				LOGGER.info("delete : username=" + credential.getUsername());
				dao.delete(credential);
			}
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
	}
}
