package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;

public interface IStudentService {

	void create(Student student);

	void update(List<StudentUpdateWrapper> updateWrappers);

	void delete(List<Student> students);

	List<Student> read(Map<String, String[]> parameters);
}
