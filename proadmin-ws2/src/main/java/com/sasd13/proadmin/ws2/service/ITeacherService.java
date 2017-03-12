package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

public interface ITeacherService {

	void create(Teacher teacher);

	void update(List<TeacherUpdateWrapper> updateWrappers);

	void delete(List<Teacher> teachers);

	List<Teacher> read(Map<String, String[]> parameters);
}
