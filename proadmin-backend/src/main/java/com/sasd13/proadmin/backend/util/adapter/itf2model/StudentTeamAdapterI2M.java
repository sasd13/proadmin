package com.sasd13.proadmin.backend.util.adapter.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.entity.Student;
import com.sasd13.proadmin.backend.entity.StudentTeam;
import com.sasd13.proadmin.backend.entity.Team;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

public class StudentTeamAdapterI2M implements IAdapter<StudentTeamBean, StudentTeam> {

	@Override
	public StudentTeam adapt(StudentTeamBean s) {
		StudentTeam t = new StudentTeam();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		Student running = new Student();
		running.setId(Long.valueOf(s.getLinkedStudent().getId()));
		t.setStudent(running);

		Team team = new Team();
		team.setId(Long.valueOf(s.getLinkedTeam().getId()));
		t.setTeam(team);

		return t;
	}
}
