package com.sasd13.proadmin.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;

public interface IStudentService {

	long create(Student student);

	void update(StudentUpdateWrapper updateWrapper);

	void delete(Student student);

	List<Student> read(Map<String, String[]> parameters);

	List<Student> readAll();
}
