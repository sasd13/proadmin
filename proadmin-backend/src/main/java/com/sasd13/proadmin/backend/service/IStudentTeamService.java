package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

public interface IStudentTeamService {

	void create(List<StudentTeamBean> studentTeamBeans);

	void delete(List<StudentTeamBean> studentTeamBeans);

	List<StudentTeamBean> read(Map<String, Object> criterias);
}
