package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.ITeacherDAO;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

public class TeacherService implements ITeacherService {

	private ITeacherDAO session;

	public TeacherService(DAO dao) {
		session = (ITeacherDAO) dao.getSession(ITeacherDAO.class);
	}

	@Override
	public long create(Teacher teacher) {
		return session.create(teacher);
	}

	@Override
	public void update(TeacherUpdateWrapper updateWrapper) {
		session.update(updateWrapper);
	}

	@Override
	public void delete(Teacher teacher) {
		session.delete(teacher);
	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) {
		return session.read(parameters);
	}

	@Override
	public List<Teacher> readAll() {
		return session.readAll();
	}
}
