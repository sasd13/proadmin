package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentTeamDeepReader extends DeepReader<StudentTeam> {

	private IStudentDAO studentDAO;
	private ITeamDAO teamDAO;
	private Map<String, String[]> parameters;

	public StudentTeamDeepReader(IStudentTeamDAO studentTeamDAO, IStudentDAO studentDAO, ITeamDAO teamDAO) {
		super(studentTeamDAO);

		this.studentDAO = studentDAO;
		this.teamDAO = teamDAO;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieveData(StudentTeam studentTeam) {
		retrieveDataStudent(studentTeam);
		retrieveDataTeam(studentTeam);
	}

	private void retrieveDataStudent(StudentTeam studentTeam) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { studentTeam.getStudent().getNumber() });

		Student student = studentDAO.select(parameters).get(0);
		studentTeam.setStudent(student);
	}

	private void retrieveDataTeam(StudentTeam studentTeam) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { studentTeam.getTeam().getNumber() });

		Team team = teamDAO.select(parameters).get(0);
		studentTeam.setTeam(team);
	}
}
