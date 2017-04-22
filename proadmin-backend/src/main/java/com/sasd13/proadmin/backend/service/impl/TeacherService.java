package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.Teacher;
import com.sasd13.proadmin.backend.dao.ITeacherDAO;
import com.sasd13.proadmin.backend.dao.dto.TeacherDTO;
import com.sasd13.proadmin.backend.service.ITeacherService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.TeacherAdapterD2B;

@Service
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
		TeacherDTO dto = teacherDAO.read(intermediary);
		TeacherAdapterD2B adapter = new TeacherAdapterD2B();

		return adapter.adapt(dto);
	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) {
		List<Teacher> list = new ArrayList<>();

		List<TeacherDTO> dtos = teacherDAO.read(parameters);
		TeacherAdapterD2B adapter = new TeacherAdapterD2B();

		for (TeacherDTO dto : dtos) {
			list.add(adapter.adapt(dto));
		}

		return list;
	}
}
