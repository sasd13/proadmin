package com.sasd13.proadmin.aaa.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ICheckService;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.aaa.bean.Profile;
import com.sasd13.proadmin.aaa.dao.IProfileDAO;

public class ProfileService implements IManageService<Profile>, IReadService<Profile> {

	private static final Logger LOGGER = Logger.getLogger(ProfileService.class);

	private IProfileDAO dao;

	public ProfileService(IProfileDAO dao) {
		this.dao = dao;
	}

	@Override
	public void create(Profile profile) {
		try {
			dao.insert(profile);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Profile> updateWrapper) {
		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Profile profile) {
		throw new NotImplementedException("Not implemented");
	}
	
	@Override
	public List<Profile> read(Map<String, String[]> parameters) {
		try {
			dao.read(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}
}
