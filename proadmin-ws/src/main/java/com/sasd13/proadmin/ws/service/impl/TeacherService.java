package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.ITeacherDAO;
import com.sasd13.proadmin.ws.service.ITeacherService;

public class TeacherService implements ITeacherService {

	private ITeacherDAO teacherDAO;

	public TeacherService(DAO dao) {
		teacherDAO = (ITeacherDAO) dao.getSession(ITeacherDAO.class);
	}

	@Override
	public long create(Teacher teacher) {
		return teacherDAO.create(teacher);
	}

	@Override
	public void update(TeacherUpdateWrapper updateWrapper) {
		teacherDAO.update(updateWrapper);
	}

	@Override
	public void delete(Teacher teacher) {
		teacherDAO.delete(teacher);
	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) {
		return teacherDAO.read(parameters);
	}

	@Override
	public List<Teacher> readAll() {
		return teacherDAO.readAll();
	}
}
