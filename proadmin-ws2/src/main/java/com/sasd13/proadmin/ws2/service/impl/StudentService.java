package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.ws2.dao.IStudentDAO;
import com.sasd13.proadmin.ws2.dao.dto.StudentDTO;
import com.sasd13.proadmin.ws2.service.IStudentService;
import com.sasd13.proadmin.ws2.util.adapter.dto2bean.StudentDTOAdapter;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private IStudentDAO studentDAO;

	@Override
	public void create(Student student) {
		studentDAO.create(student);
	}

	@Override
	public void update(List<StudentUpdateWrapper> updateWrappers) {
		studentDAO.update(updateWrappers);
	}

	@Override
	public void delete(List<Student> students) {
		studentDAO.delete(students);
	}

	@Override
	public List<Student> read(Map<String, String[]> parameters) {
		List<Student> students = new ArrayList<>();

		List<StudentDTO> dtos = studentDAO.read(parameters);
		StudentDTOAdapter adapter = new StudentDTOAdapter();

		for (StudentDTO dto : dtos) {
			students.add(adapter.adapt(dto));
		}

		return students;
	}
}
