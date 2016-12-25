package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.ITeacherDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.ws2.service.ITeacherService;

@Service
public class TeacherService implements ITeacherService {

	private static final Logger LOGGER = Logger.getLogger(TeacherService.class);

	@Autowired
	private ITeacherDAO dao;

	@Override
	public void create(Teacher teacher) throws ServiceException {
		LOGGER.info("create : number=" + teacher.getNumber());

		try {
			dao.insert(teacher);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Teacher> updateWrapper) throws ServiceException {
		LOGGER.info("update : number=" + ((TeacherUpdateWrapper) updateWrapper).getNumber());

		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Teacher teacher) throws ServiceException {
		LOGGER.info("delete : number=" + teacher.getNumber());

		try {
			dao.delete(teacher);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) throws ServiceException {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Teacher> list = new ArrayList<>();

		try {
			list = dao.select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return list;
	}

	@Override
	public List<Teacher> readAll() throws ServiceException {
		LOGGER.info("readAll");

		List<Teacher> list = new ArrayList<>();

		try {
			list = dao.selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return list;
	}

	@Override
	public Teacher find(String number) {
		Map<String, String[]> parameters = new HashMap<>();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { number });

		List<Teacher> results = read(parameters);

		return results.isEmpty() ? null : results.get(0);
	}
}
