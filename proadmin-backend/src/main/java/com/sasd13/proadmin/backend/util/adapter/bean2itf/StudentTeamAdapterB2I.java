package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.StudentTeam;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.LinkedTeam;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

public class StudentTeamAdapterB2I implements IAdapter<StudentTeam, StudentTeamBean> {

	@Override
	public StudentTeamBean adapt(StudentTeam s) {
		StudentTeamBean t = new StudentTeamBean();

		LinkedStudent linkedStudent = new LinkedStudent();
		linkedStudent.setId(String.valueOf(s.getStudent().getId()));
		t.setLinkedStudent(linkedStudent);

		LinkedTeam linkedTeam = new LinkedTeam();
		linkedTeam.setId(String.valueOf(s.getTeam().getId()));
		t.setLinkedTeam(linkedTeam);

		return t;
	}
}
