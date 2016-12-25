package com.sasd13.proadmin.ws2.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;

@Service
public class TeacherService implements IManageService<Teacher>, IReadService<Teacher> {

	@Override
	public void create(List<Teacher> teachers) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(List<IUpdateWrapper<Teacher>> updateWrappers) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<Teacher> teachers) throws ServiceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Teacher> read(Map<String, String[]> parameters) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Teacher> deepRead(Map<String, String[]> parameters) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Teacher> readAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> deepReadAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
