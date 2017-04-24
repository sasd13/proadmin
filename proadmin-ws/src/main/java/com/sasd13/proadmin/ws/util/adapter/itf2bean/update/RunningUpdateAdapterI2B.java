package com.sasd13.proadmin.ws.util.adapter.itf2bean.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.running.RunningBean;
import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.Teacher;
import com.sasd13.proadmin.ws.bean.update.RunningUpdate;

public class RunningUpdateAdapterI2B implements IAdapter<RunningBean, RunningUpdate> {

	@Override
	public RunningUpdate adapt(RunningBean s) {
		RunningUpdate t = new RunningUpdate();

		t.setYear(s.getId().getLinkedId().getYearStarted());
		t.setProjectCode(s.getId().getLinkedId().getProjectCode());
		t.setTeacherIntermediary(s.getId().getLinkedId().getTeacherIntermediary());

		Running running = new Running();
		running.setYear(s.getCoreInfo().getYearStarted());
		t.setWrapped(running);

		Project project = new Project();
		project.setCode(s.getLinkedProject().getCode());
		running.setProject(project);

		Teacher teacher = new Teacher();
		teacher.setIntermediary(s.getLinkedTeacher().getIntermediary());
		running.setTeacher(teacher);

		return t;
	}
}
