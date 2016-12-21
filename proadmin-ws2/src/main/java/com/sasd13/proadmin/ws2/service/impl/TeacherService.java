package com.sasd13.proadmin.ws2.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws2.service.ITeacherService;

@Service
public class TeacherService implements ITeacherService {

	@Override
	public void create(Teacher teacher) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String number, Teacher teacher) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String number) throws ServiceException {
		// TODO Auto-generated method stub

	}
}
