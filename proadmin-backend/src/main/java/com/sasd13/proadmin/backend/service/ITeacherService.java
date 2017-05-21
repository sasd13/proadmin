package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public interface ITeacherService {

	void create(TeacherBean teacherBean);

	void update(TeacherBean teacherBean);

	void delete(TeacherBean teacherBean);

	TeacherBean read(String intermediary);

	List<TeacherBean> read(Map<String, Object> criterias);
}
