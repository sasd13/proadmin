package com.sasd13.proadmin.ws.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.bean.StudentTeam;
import com.sasd13.proadmin.ws.bean.Team;

public class StudentTeamAdapterI2B implements IAdapter<StudentTeamBean, StudentTeam> {

	@Override
	public StudentTeam adapt(StudentTeamBean s) {
		StudentTeam t = new StudentTeam();

		Student student = new Student();
		student.setIntermediary(s.getLinkedStudent().getIntermediary());
		t.setStudent(student);

		Team team = new Team();
		team.setNumber(s.getLinkedTeam().getNumber());
		t.setTeam(team);

		return t;
	}
}
