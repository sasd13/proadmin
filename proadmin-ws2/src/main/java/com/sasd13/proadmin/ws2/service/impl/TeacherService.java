package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.ITeacherUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.ITeacherDAO;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.TeacherDTOAdapter;
import com.sasd13.proadmin.ws2.service.ITeacherService;

@Service
public class TeacherService implements ITeacherService {

	@Autowired
	private ITeacherDAO teacherDAO;

	@Override
	public void create(Teacher teacher) {
		teacherDAO.create(teacher);
	}

	@Override
	public void update(ITeacherUpdateWrapper updateWrapper) {
		teacherDAO.update(updateWrapper);
	}

	@Override
	public void delete(Teacher teacher) {
		teacherDAO.delete(teacher);
	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) {
		List<Teacher> teachers = new ArrayList<>();

		List<TeacherDTO> dtos = teacherDAO.read(parameters);
		TeacherDTOAdapter adapter = new TeacherDTOAdapter();

		for (TeacherDTO dto : dtos) {
			teachers.add(adapter.adapt(dto));
		}

		return teachers;
	}
}
