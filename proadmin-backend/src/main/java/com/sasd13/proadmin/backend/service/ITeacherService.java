package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.model.Teacher;

public interface ITeacherService {

	void create(Teacher teacher);

	void update(Teacher teacher);

	void delete(Teacher teacher);

	Teacher read(String intermediary);

	List<Teacher> read(Map<String, Object> criterias);
}
