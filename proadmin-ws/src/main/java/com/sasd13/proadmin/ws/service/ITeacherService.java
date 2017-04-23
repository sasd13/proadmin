package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

public interface ITeacherService {

	long create(ITeacher iTeacher);

	void update(TeacherUpdateWrapper updateWrapper);

	void delete(ITeacher iTeacher);

	List<ITeacher> read(Map<String, String[]> parameters);

	List<ITeacher> readAll();
}
