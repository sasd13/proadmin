package com.sasd13.proadmin.aaa.service;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.ICheckService;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;

public class CredentialService implements IManageService<Credential>, ICheckService<Credential> {

	private static final Logger LOGGER = Logger.getLogger(CredentialService.class);

	private ICredentialDAO dao;

	public CredentialService(ICredentialDAO dao) {
		this.dao = dao;
	}

	@Override
	public void create(Credential credential) {
		try {
			dao.insert(credential);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Credential> updateWrapper) {
		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Credential credential) {
		try {
			dao.delete(credential);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public boolean contains(Credential credential) {
		boolean contains = false;

		try {
			contains = dao.contains(credential);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return contains;
	}
}
