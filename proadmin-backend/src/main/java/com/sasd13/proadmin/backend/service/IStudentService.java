package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.model.Student;

public interface IStudentService {

	void create(Student student);

	void update(Student student);

	void delete(Student student);

	Student read(String intermediary);

	List<Student> read(Map<String, Object> criterias);
}
