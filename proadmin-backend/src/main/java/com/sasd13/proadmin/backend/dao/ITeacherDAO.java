package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Teacher;
import com.sasd13.proadmin.backend.dao.dto.TeacherDTO;

public interface ITeacherDAO {

	TeacherDTO create(Teacher teacher);

	void update(Teacher teacher);

	void delete(Teacher teacher);

	TeacherDTO read(String intermediary);

	List<TeacherDTO> read(Map<String, String[]> parameters);
}
