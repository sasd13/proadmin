package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.ITeacherDAO;
import com.sasd13.proadmin.backend.model.Teacher;
import com.sasd13.proadmin.backend.service.ITeacherService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherService implements ITeacherService {

	@Autowired
	private ITeacherDAO teacherDAO;

	@Override
	public void create(Teacher teacher) {
		teacherDAO.create(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		teacherDAO.update(teacher);
	}

	@Override
	public void delete(Teacher teacher) {
		teacherDAO.delete(teacher);
	}

	@Override
	public Teacher read(String intermediary) {
		return teacherDAO.read(intermediary);
	}

	@Override
	public List<Teacher> read(Map<String, Object> criterias) {
		return teacherDAO.read(criterias);
	}
}
