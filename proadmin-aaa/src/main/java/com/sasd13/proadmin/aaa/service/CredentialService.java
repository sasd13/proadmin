package com.sasd13.proadmin.aaa.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.ICheckService;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.aaa.dao.DAOManager;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;

public class CredentialService implements IManageService<Credential>, ICheckService<Credential> {

	private static final Logger LOGGER = Logger.getLogger(CredentialService.class);

	private ICredentialDAO dao;

	public CredentialService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Credential credential) {
		try {
			dao.open();
			dao.insert(credential);
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
	public void update(IUpdateWrapper<Credential> updateWrapper) {
		try {
			dao.open();
			dao.update(updateWrapper);
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
	public void delete(Credential credential) {
		try {
			dao.open();
			dao.delete(credential);
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
	public boolean contains(Credential credential) {
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
