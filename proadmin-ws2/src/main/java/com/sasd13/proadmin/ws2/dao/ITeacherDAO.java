package com.sasd13.proadmin.ws2.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.ws2.dao.dto.TeacherDTO;

public interface ITeacherDAO {

	TeacherDTO create(Teacher teacher);

	void update(List<TeacherUpdateWrapper> updateWrappers);

	void delete(List<Teacher> teachers);

	List<TeacherDTO> read(Map<String, String[]> parameters);
}
