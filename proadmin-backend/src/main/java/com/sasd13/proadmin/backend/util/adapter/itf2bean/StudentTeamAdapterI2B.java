package com.sasd13.proadmin.backend.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Student;
import com.sasd13.proadmin.backend.bean.StudentTeam;
import com.sasd13.proadmin.backend.bean.Team;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

public class StudentTeamAdapterI2B implements IAdapter<StudentTeamBean, StudentTeam> {

	@Override
	public StudentTeam adapt(StudentTeamBean s) {
		StudentTeam t = new StudentTeam();

		Student running = new Student();
		running.setId(Long.valueOf(s.getLinkedStudent().getId()));
		t.setStudent(running);

		Team team = new Team();
		team.setId(Long.valueOf(s.getLinkedTeam().getId()));
		t.setTeam(team);

		return t;
	}
}
