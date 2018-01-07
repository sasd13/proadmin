package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IStudentTeamDAO;
import com.sasd13.proadmin.backend.entity.StudentTeam;
import com.sasd13.proadmin.backend.service.IStudentTeamService;
import com.sasd13.proadmin.backend.util.adapter.entity2itf.StudentTeamAdapterM2I;
import com.sasd13.proadmin.backend.util.adapter.itf2entity.StudentTeamAdapterI2M;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StudentTeamService implements IStudentTeamService {

	@Autowired
	private IStudentTeamDAO studentTeamDAO;

	@Override
	public void create(List<StudentTeamBean> studentTeamBeans) {
		List<StudentTeam> studentTeams = adaptI2M(studentTeamBeans);

		studentTeamDAO.create(studentTeams);
	}

	private List<StudentTeam> adaptI2M(List<StudentTeamBean> studentTeamBeans) {
		List<StudentTeam> list = new ArrayList<>();
		StudentTeamAdapterI2M adapter = new StudentTeamAdapterI2M();

		for (StudentTeamBean studentTeamBean : studentTeamBeans) {
			list.add(adapter.adapt(studentTeamBean));
		}

		return list;
	}

	@Override
	public void delete(List<StudentTeamBean> studentTeamBeans) {
		List<StudentTeam> studentTeams = adaptI2M(studentTeamBeans);

		studentTeamDAO.delete(studentTeams);
	}

	@Override
	public List<StudentTeamBean> read(Map<String, Object> criterias) {
		List<StudentTeam> studentTeams = studentTeamDAO.read(criterias);

		return adaptM2I(studentTeams);
	}

	private List<StudentTeamBean> adaptM2I(List<StudentTeam> studentTeams) {
		List<StudentTeamBean> list = new ArrayList<>();
		StudentTeamAdapterM2I adapter = new StudentTeamAdapterM2I();

		for (StudentTeam studentTeam : studentTeams) {
			list.add(adapter.adapt(studentTeam));
		}

		return list;
	}
}
