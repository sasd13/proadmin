package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Teacher;
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
	public long create(Teacher teacher) {
		return teacherDAO.create(teacher);
	}

	@Override
	public void update(TeacherUpdate teacherUpdate) {
		teacherDAO.update(teacherUpdate);
	}

	@Override
	public void delete(Teacher teacher) {
		teacherDAO.delete(teacher);
	}

	@Override
	public List<Teacher> read(Map<String, String[]> criterias) {
		return teacherDAO.read(criterias);
	}
}
