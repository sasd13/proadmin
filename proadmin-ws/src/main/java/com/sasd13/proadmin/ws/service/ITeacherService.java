package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Teacher;
import com.sasd13.proadmin.ws.bean.update.TeacherUpdate;

public interface ITeacherService {

	long create(Teacher teacher);

	void update(TeacherUpdate teacherUpdate);

	void delete(Teacher teacher);

	List<Teacher> read(Map<String, String[]> parameters);

	List<Teacher> readAll();
}
