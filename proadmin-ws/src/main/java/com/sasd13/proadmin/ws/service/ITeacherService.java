package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.ws.bean.update.TeacherUpdate;

public interface ITeacherService {

	long create(ITeacher iTeacher);

	void update(TeacherUpdate updateWrapper);

	void delete(ITeacher iTeacher);

	List<ITeacher> read(Map<String, String[]> parameters);

	List<ITeacher> readAll();
}
