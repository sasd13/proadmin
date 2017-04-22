package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.Student;
import com.sasd13.proadmin.backend.dao.IStudentDAO;
import com.sasd13.proadmin.backend.dao.dto.StudentDTO;
import com.sasd13.proadmin.backend.service.IStudentService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.StudentAdapterD2B;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private IStudentDAO studentDAO;

	@Override
	public void create(Student student) {
		studentDAO.create(student);
	}

	@Override
	public void update(Student student) {
		studentDAO.update(student);
	}

	@Override
	public void delete(Student student) {
		studentDAO.delete(student);
	}

	@Override
	public Student read(String intermediary) {
		StudentDTO dto = studentDAO.read(intermediary);
		StudentAdapterD2B adapter = new StudentAdapterD2B();

		return adapter.adapt(dto);
	}

	@Override
	public List<Student> read(Map<String, String[]> parameters) {
		List<Student> list = new ArrayList<>();

		List<StudentDTO> dtos = studentDAO.read(parameters);
		StudentAdapterD2B adapter = new StudentAdapterD2B();

		for (StudentDTO dto : dtos) {
			list.add(adapter.adapt(dto));
		}

		return list;
	}
}
