package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.IStudentDAO;
import com.sasd13.proadmin.ws.service.IStudentService;

public class StudentService implements IStudentService {

	private IStudentDAO studentDAO;

	public StudentService(DAO dao) {
		studentDAO = (IStudentDAO) dao.getSession(IStudentDAO.class);
	}

	@Override
	public long create(Student student) {
		return studentDAO.create(student);
	}

	@Override
	public void update(StudentUpdateWrapper updateWrapper) {
		studentDAO.update(updateWrapper);
	}

	@Override
	public void delete(Student student) {
		studentDAO.delete(student);
	}

	@Override
	public List<Student> read(Map<String, String[]> parameters) {
		return studentDAO.read(parameters);
	}

	@Override
	public List<Student> readAll() {
		return studentDAO.readAll();
	}
}