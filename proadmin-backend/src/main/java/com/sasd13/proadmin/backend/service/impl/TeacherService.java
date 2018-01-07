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
import com.sasd13.proadmin.backend.util.adapter.itf.TeacherITFAdapter;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherService implements ITeacherService {

	@Autowired
	private ITeacherDAO teacherDAO;

	private TeacherITFAdapter adapter;

	public TeacherService() {
		adapter = new TeacherITFAdapter();
	}

	@Override
	public void create(TeacherBean teacherBean) {
		Teacher teacher = adapter.adaptI2M(teacherBean);

		teacherDAO.create(teacher);
	}

	@Override
	public void update(TeacherBean teacherBean) {
		Teacher teacher = adapter.adaptI2M(teacherBean);

		teacherDAO.update(teacher);
	}

	@Override
	public void delete(TeacherBean teacherBean) {
		Teacher teacher = adapter.adaptI2M(teacherBean);

		teacherDAO.delete(teacher);
	}

	@Override
	public TeacherBean read(String intermediary) {
		Teacher teacher = teacherDAO.read(intermediary);

		return teacher != null ? adapter.adaptM2I(teacher) : null;
	}

	@Override
	public List<TeacherBean> read(Map<String, Object> criterias) {
		List<Teacher> teachers = teacherDAO.read(criterias);

		return adapter.adaptM2I(teachers);
	}
}
