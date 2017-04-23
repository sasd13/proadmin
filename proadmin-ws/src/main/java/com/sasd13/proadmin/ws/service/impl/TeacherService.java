package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.ws.bean.update.TeacherUpdate;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.ITeacherDAO;
import com.sasd13.proadmin.ws.service.ITeacherService;

public class TeacherService implements ITeacherService {

	private ITeacherDAO teacherDAO;

	public TeacherService(DAO dao) {
		teacherDAO = (ITeacherDAO) dao.getSession(ITeacherDAO.class);
	}

	@Override
	public long create(ITeacher iTeacher) {
		return teacherDAO.create(iTeacher);
	}

	@Override
	public void update(TeacherUpdate updateWrapper) {
		teacherDAO.update(updateWrapper);
	}

	@Override
	public void delete(ITeacher iTeacher) {
		teacherDAO.delete(iTeacher);
	}

	@Override
	public List<ITeacher> read(Map<String, String[]> parameters) {
		return teacherDAO.read(parameters);
	}

	@Override
	public List<ITeacher> readAll() {
		return teacherDAO.readAll();
	}
}
