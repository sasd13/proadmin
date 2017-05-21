package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.student.StudentBean;

public interface IStudentService {

	void create(StudentBean studentBean);

	void update(StudentBean studentBean);

	void delete(StudentBean studentBean);

	StudentBean read(String intermediary);

	List<StudentBean> read(Map<String, Object> criterias);
}
