package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IStudentDAO;
import com.sasd13.proadmin.ws2.db.dto.StudentDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.StudentDTOAdapter;
import com.sasd13.proadmin.ws2.service.IStudentService;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private IStudentDAO studentDAO;

	@Override
	public void create(Student student) {
		studentDAO.create(student);
	}

	@Override
	public void update(IStudentUpdateWrapper updateWrapper) {
		studentDAO.update(updateWrapper);
	}

	@Override
	public void delete(Student student) {
		studentDAO.delete(student);
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
