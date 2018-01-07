package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IStudentDAO;
import com.sasd13.proadmin.backend.model.Student;
import com.sasd13.proadmin.backend.service.IStudentService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.StudentAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.StudentAdapterM2I;
import com.sasd13.proadmin.itf.bean.student.StudentBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StudentService implements IStudentService {

	@Autowired
	private IStudentDAO studentDAO;

	@Override
	public void create(StudentBean studentBean) {
		Student student = adaptI2M(studentBean);

		studentDAO.create(student);
	}

	private Student adaptI2M(StudentBean studentBean) {
		return new StudentAdapterI2M().adapt(studentBean);
	}

	@Override
	public void update(StudentBean studentBean) {
		Student student = adaptI2M(studentBean);

		studentDAO.update(student);
	}

	@Override
	public void delete(StudentBean studentBean) {
		Student student = adaptI2M(studentBean);

		studentDAO.delete(student);
	}

	@Override
	public StudentBean read(String intermediary) {
		Student student = studentDAO.read(intermediary);

		return student != null ? new StudentAdapterM2I().adapt(student) : null;
	}

	@Override
	public List<StudentBean> read(Map<String, Object> criterias) {
		List<Student> students = studentDAO.read(criterias);

		return adaptM2I(students);
	}

	private List<StudentBean> adaptM2I(List<Student> students) {
		List<StudentBean> list = new ArrayList<>();
		StudentAdapterM2I adapter = new StudentAdapterM2I();

		for (Student student : students) {
			list.add(adapter.adapt(student));
		}

		return list;
	}
}
