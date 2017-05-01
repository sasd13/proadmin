package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IStudentDAO;
import com.sasd13.proadmin.backend.model.Student;
import com.sasd13.proadmin.backend.service.IStudentService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
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
		return studentDAO.read(intermediary);
	}

	@Override
	public List<Student> read(Map<String, Object> criterias) {
		return studentDAO.read(criterias);
	}
}
