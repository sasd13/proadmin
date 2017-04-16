package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.IStudentDAO;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;

public class StudentService implements IStudentService {

	private IStudentDAO session;

	public StudentService(DAO dao) {
		session = (IStudentDAO) dao.getSession(IStudentDAO.class);
	}

	@Override
	public long create(Student student) {
		return session.create(student);
	}

	@Override
	public void update(StudentUpdateWrapper updateWrapper) {
		session.update(updateWrapper);
	}

	@Override
	public void delete(Student student) {
		session.delete(student);
	}

	@Override
	public List<Student> read(Map<String, String[]> parameters) {
		return session.read(parameters);
	}

	@Override
	public List<Student> readAll() {
		return session.readAll();
	}
}
