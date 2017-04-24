package com.sasd13.proadmin.ws.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.LinkedTeam;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;
import com.sasd13.proadmin.ws.bean.StudentTeam;

public class StudentTeamAdapterB2I implements IAdapter<StudentTeam, StudentTeamBean> {

	@Override
	public StudentTeamBean adapt(StudentTeam s) {
		StudentTeamBean t = new StudentTeamBean();

		LinkedStudent linkedStudent = new LinkedStudent();
		linkedStudent.setIntermediary(s.getStudent().getIntermediary());
		t.setLinkedStudent(linkedStudent);

		LinkedTeam linkedTeam = new LinkedTeam();
		linkedTeam.setNumber(s.getTeam().getNumber());
		t.setLinkedTeam(linkedTeam);

		return t;
	}
}
