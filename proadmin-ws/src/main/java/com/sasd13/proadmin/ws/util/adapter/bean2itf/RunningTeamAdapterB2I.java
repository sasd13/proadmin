package com.sasd13.proadmin.ws.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.LinkedAcademicLevel;
import com.sasd13.proadmin.itf.bean.LinkedProject;
import com.sasd13.proadmin.itf.bean.LinkedRunning;
import com.sasd13.proadmin.itf.bean.LinkedTeacher;
import com.sasd13.proadmin.itf.bean.LinkedTeam;
import com.sasd13.proadmin.itf.bean.runningteam.Id;
import com.sasd13.proadmin.itf.bean.runningteam.LinkedId;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;
import com.sasd13.proadmin.ws.bean.RunningTeam;

public class RunningTeamAdapterB2I implements IAdapter<RunningTeam, RunningTeamBean> {

	@Override
	public RunningTeamBean adapt(RunningTeam s) {
		RunningTeamBean t = new RunningTeamBean();

		Id id = new Id();
		t.setId(id);

		LinkedId linkedId = new LinkedId();
		linkedId.setYearStarted(s.getRunning().getYear());
		linkedId.setProjectCode(s.getRunning().getProject().getCode());
		linkedId.setTeacherIntermediary(s.getRunning().getTeacher().getIntermediary());
		linkedId.setTeamNumber(s.getTeam().getNumber());
		linkedId.setAcademicLevelCode(s.getAcademicLevel().getCode());
		id.setLinkedId(linkedId);

		LinkedRunning linkedRunning = new LinkedRunning();
		linkedRunning.setYearStarted(s.getRunning().getYear());
		t.setLinkedRunning(linkedRunning);

		LinkedProject linkedProject = new LinkedProject();
		linkedProject.setCode(s.getRunning().getProject().getCode());
		linkedRunning.setLinkedProject(linkedProject);

		LinkedTeacher linkedTeacher = new LinkedTeacher();
		linkedTeacher.setIntermediary(s.getRunning().getTeacher().getIntermediary());
		linkedRunning.setLinkedTeacher(linkedTeacher);

		LinkedTeam linkedTeam = new LinkedTeam();
		linkedTeam.setNumber(s.getTeam().getNumber());
		t.setLinkedTeam(linkedTeam);

		LinkedAcademicLevel linkedAcademicLevel = new LinkedAcademicLevel();
		linkedAcademicLevel.setCode(s.getAcademicLevel().getCode());
		t.setLinkedAcademicLevel(linkedAcademicLevel);

		return t;
	}
}
