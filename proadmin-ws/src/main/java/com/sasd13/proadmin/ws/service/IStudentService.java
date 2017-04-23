package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.ws.bean.update.StudentUpdate;

public interface IStudentService {

	long create(Student student);

	void update(StudentUpdate updateWrapper);

	void delete(Student student);

	List<Student> read(Map<String, String[]> parameters);

	List<Student> readAll();
}
