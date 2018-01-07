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
import com.sasd13.proadmin.backend.util.adapter.itf.StudentITFAdapter;
import com.sasd13.proadmin.itf.bean.student.StudentBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StudentService implements IStudentService {

	@Autowired
	private IStudentDAO studentDAO;

	private StudentITFAdapter adapter;

	public StudentService() {
		adapter = new StudentITFAdapter();
	}

	@Override
	public void create(StudentBean studentBean) {
		Student student = adapter.adaptI2M(studentBean);

		studentDAO.create(student);
	}

	@Override
	public void update(StudentBean studentBean) {
		Student student = adapter.adaptI2M(studentBean);

		studentDAO.update(student);
	}

	@Override
	public void delete(StudentBean studentBean) {
		Student student = adapter.adaptI2M(studentBean);

		studentDAO.delete(student);
	}

	@Override
	public StudentBean read(String intermediary) {
		Student student = studentDAO.read(intermediary);

		return student != null ? adapter.adaptM2I(student) : null;
	}

	@Override
	public List<StudentBean> read(Map<String, Object> criterias) {
		List<Student> students = studentDAO.read(criterias);

		return adapter.adaptM2I(students);
	}
}
