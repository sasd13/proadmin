package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IStudentTeamDAO;
import com.sasd13.proadmin.backend.model.StudentTeam;
import com.sasd13.proadmin.backend.service.IStudentTeamService;
import com.sasd13.proadmin.backend.util.adapter.itf.StudentTeamITFAdapter;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StudentTeamService implements IStudentTeamService {

	@Autowired
	private IStudentTeamDAO studentTeamDAO;

	private StudentTeamITFAdapter adapter;

	public StudentTeamService() {
		adapter = new StudentTeamITFAdapter();
	}

	@Override
	public void create(List<StudentTeamBean> studentTeamBeans) {
		List<StudentTeam> studentTeams = adapter.adaptI2M(studentTeamBeans);

		studentTeamDAO.create(studentTeams);
	}

	@Override
	public void delete(List<StudentTeamBean> studentTeamBeans) {
		List<StudentTeam> studentTeams = adapter.adaptI2M(studentTeamBeans);

		studentTeamDAO.delete(studentTeams);
	}

	@Override
	public List<StudentTeamBean> read(Map<String, Object> criterias) {
		List<StudentTeam> studentTeams = studentTeamDAO.read(criterias);

		return adapter.adaptM2I(studentTeams);
	}
}
