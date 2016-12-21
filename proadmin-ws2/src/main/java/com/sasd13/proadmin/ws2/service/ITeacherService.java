package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;

public interface ITeacherService {

	void create(Teacher teacher) throws ServiceException;
	
	List<Teacher> read(Map<String, String[]> parameters) throws ServiceException;
	
	void update(String number, Teacher teacher) throws ServiceException;
	
	void delete(String number) throws ServiceException;
}
