package com.sasd13.proadmin.backend.util.adapter.entity2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.entity.RunningTeam;
import com.sasd13.proadmin.itf.bean.LinkedAcademicLevel;
import com.sasd13.proadmin.itf.bean.LinkedProject;
import com.sasd13.proadmin.itf.bean.LinkedRunning;
import com.sasd13.proadmin.itf.bean.LinkedTeacher;
import com.sasd13.proadmin.itf.bean.LinkedTeam;
import com.sasd13.proadmin.itf.bean.runningteam.Id;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamAdapterM2I implements IAdapter<RunningTeam, RunningTeamBean> {

	@Override
	public RunningTeamBean adapt(RunningTeam s) {
		RunningTeamBean t = new RunningTeamBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		LinkedRunning linkedRunning = new LinkedRunning();
		linkedRunning.setId(String.valueOf(s.getRunning().getId()));
		linkedRunning.setYearStarted(s.getRunning().getYear());
		t.setLinkedRunning(linkedRunning);

		LinkedProject linkedProject = new LinkedProject();
		linkedProject.setId(String.valueOf(s.getRunning().getProject().getId()));
		linkedProject.setCode(s.getRunning().getProject().getCode());
		linkedRunning.setLinkedProject(linkedProject);

		LinkedTeacher linkedTeacher = new LinkedTeacher();
		linkedTeacher.setId(String.valueOf(s.getRunning().getTeacher().getId()));
		linkedTeacher.setIntermediary(s.getRunning().getTeacher().getIntermediary());
		linkedRunning.setLinkedTeacher(linkedTeacher);

		LinkedTeam linkedTeam = new LinkedTeam();
		linkedTeam.setId(String.valueOf(s.getTeam().getId()));
		linkedTeam.setNumber(s.getTeam().getNumber());
		t.setLinkedTeam(linkedTeam);

		LinkedAcademicLevel linkedAcademicLevel = new LinkedAcademicLevel();
		linkedAcademicLevel.setId(String.valueOf(s.getAcademicLevel().getId()));
		linkedAcademicLevel.setCode(s.getAcademicLevel().getCode());
		t.setLinkedAcademicLevel(linkedAcademicLevel);

		return t;
	}
}
