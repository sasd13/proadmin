package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;

public interface IStudentService {

	void create(Student student);

	void update(IStudentUpdateWrapper updateWrapper);

	void delete(Student student);

	List<Student> read(Map<String, String[]> parameters);
}
