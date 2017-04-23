package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.IStudentTeam;
import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.dao.IStudentDAO;
import com.sasd13.proadmin.ws.dao.IStudentTeamDAO;
import com.sasd13.proadmin.ws.dao.ITeamDAO;

public class StudentTeamDeepReader extends DeepReader<IStudentTeam> {

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
	protected void retrieve(IStudentTeam iStudentTeam) {
		retrieveDataStudent(iStudentTeam);
		retrieveDataTeam(iStudentTeam);
	}

	private void retrieveDataStudent(IStudentTeam iStudentTeam) {
		parameters.clear();
		parameters.put(EnumParameter.INTERMEDIARY.getName(), new String[] { iStudentTeam.getStudent().getIntermediary() });

		Student student = studentDAO.read(parameters).get(0);
		iStudentTeam.setStudent(student);
	}

	private void retrieveDataTeam(IStudentTeam iStudentTeam) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { iStudentTeam.getTeam().getNumber() });

		ITeam iTeam = teamDAO.read(parameters).get(0);
		iStudentTeam.setTeam(iTeam);
	}
}
