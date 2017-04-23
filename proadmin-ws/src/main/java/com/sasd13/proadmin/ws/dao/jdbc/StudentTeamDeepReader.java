package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.bean.StudentTeam;
import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.dao.IStudentDAO;
import com.sasd13.proadmin.ws.dao.IStudentTeamDAO;
import com.sasd13.proadmin.ws.dao.ITeamDAO;

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
	protected void retrieve(StudentTeam studentTeam) {
		retrieveDataStudent(studentTeam);
		retrieveDataTeam(studentTeam);
	}

	private void retrieveDataStudent(StudentTeam studentTeam) {
		parameters.clear();
		parameters.put(EnumParameter.INTERMEDIARY.getName(), new String[] { studentTeam.getStudent().getIntermediary() });

		Student student = studentDAO.read(parameters).get(0);
		studentTeam.setStudent(student);
	}

	private void retrieveDataTeam(StudentTeam studentTeam) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { studentTeam.getTeam().getNumber() });

		Team team = teamDAO.read(parameters).get(0);
		studentTeam.setTeam(team);
	}
}
