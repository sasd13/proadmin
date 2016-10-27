package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.Binder;

public class StudentTeamDeepReader extends DeepReader<StudentTeam> {

	private IStudentDAO studentDAO;
	private ITeamDAO teamDAO;

	public StudentTeamDeepReader(IStudentTeamDAO studentTeamDAO, IStudentDAO studentDAO, ITeamDAO teamDAO) {
		super(studentTeamDAO);

		this.studentDAO = studentDAO;
		this.teamDAO = teamDAO;
	}

	@Override
	protected void retrieveData(StudentTeam studentTeam) throws DAOException {
		Student student = studentDAO.select(studentTeam.getStudent().getId());
		Binder.bind(studentTeam.getStudent(), student);

		Team team = teamDAO.select(studentTeam.getTeam().getId());
		Binder.bind(studentTeam.getTeam(), team);
	}
}
