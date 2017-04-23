package com.sasd13.proadmin.ws.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.running.RunningBean;
import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.Teacher;

public class RunningAdapterI2B implements IAdapter<RunningBean, Running> {

	@Override
	public Running adapt(RunningBean s) {
		Running t = new Running();

		t.setYear(s.getCoreInfo().getYearStarted());

		Project project = new Project();
		project.setCode(s.getId().getLinkedId().getProjectCode());
		t.setProject(project);

		Teacher teacher = new Teacher();
		teacher.setIntermediary(s.getId().getLinkedId().getTeacherIntermediary());
		t.setTeacher(teacher);

		return t;
	}
}
