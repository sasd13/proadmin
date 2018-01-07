package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.ITeacherDAO;
import com.sasd13.proadmin.backend.entity.Teacher;
import com.sasd13.proadmin.backend.service.ITeacherService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.TeacherAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.TeacherAdapterM2I;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherService implements ITeacherService {

	@Autowired
	private ITeacherDAO teacherDAO;

	@Override
	public void create(TeacherBean teacherBean) {
		Teacher teacher = adaptI2M(teacherBean);

		teacherDAO.create(teacher);
	}

	private Teacher adaptI2M(TeacherBean teacherBean) {
		return new TeacherAdapterI2M().adapt(teacherBean);
	}

	@Override
	public void update(TeacherBean teacherBean) {
		Teacher teacher = adaptI2M(teacherBean);

		teacherDAO.update(teacher);
	}

	@Override
	public void delete(TeacherBean teacherBean) {
		Teacher teacher = adaptI2M(teacherBean);

		teacherDAO.delete(teacher);
	}

	@Override
	public TeacherBean read(String intermediary) {
		Teacher teacher = teacherDAO.read(intermediary);

		return teacher != null ? new TeacherAdapterM2I().adapt(teacher) : null;
	}

	@Override
	public List<TeacherBean> read(Map<String, Object> criterias) {
		List<Teacher> teachers = teacherDAO.read(criterias);

		return adaptM2I(teachers);
	}

	private List<TeacherBean> adaptM2I(List<Teacher> teachers) {
		List<TeacherBean> list = new ArrayList<>();
		TeacherAdapterM2I adapter = new TeacherAdapterM2I();

		for (Teacher teacher : teachers) {
			list.add(adapter.adapt(teacher));
		}

		return list;
	}
}
