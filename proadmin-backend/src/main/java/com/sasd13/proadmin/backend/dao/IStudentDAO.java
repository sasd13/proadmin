package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.model.Student;

public interface IStudentDAO {

	Student create(Student student);

	void update(Student student);

	void delete(Student student);

	Student read(String intermediary);

	List<Student> read(Map<String, String[]> parameters);
}
