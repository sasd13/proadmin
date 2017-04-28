package com.sasd13.proadmin.backend.util.adapter.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.model.Project;
import com.sasd13.proadmin.backend.model.Running;
import com.sasd13.proadmin.backend.model.Teacher;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningAdapterI2M implements IAdapter<RunningBean, Running> {

	@Override
	public Running adapt(RunningBean s) {
		Running t = new Running();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		t.setYear(s.getCoreInfo().getYearStarted());

		Project project = new Project();
		project.setId(Long.valueOf(s.getLinkedProject().getId()));
		t.setProject(project);

		Teacher teacher = new Teacher();
		teacher.setId(Long.valueOf(s.getLinkedTeacher().getId()));
		t.setTeacher(teacher);

		return t;
	}
}
