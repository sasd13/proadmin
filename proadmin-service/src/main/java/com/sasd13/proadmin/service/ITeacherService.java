package com.sasd13.proadmin.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

public interface ITeacherService {

	long create(Teacher teacher);

	void update(TeacherUpdateWrapper updateWrapper);

	void delete(Teacher teacher);

	List<Teacher> read(Map<String, String[]> parameters);

	List<Teacher> readAll();
}
