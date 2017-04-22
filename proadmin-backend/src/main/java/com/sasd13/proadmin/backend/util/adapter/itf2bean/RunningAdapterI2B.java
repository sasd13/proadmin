package com.sasd13.proadmin.backend.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningAdapterI2B implements IAdapter<RunningBean, Running> {

	private ProjectAdapterI2B projectAdapter;
	private TeacherAdapterI2B teacherAdapter;

	public RunningAdapterI2B() {
		projectAdapter = new ProjectAdapterI2B();
		teacherAdapter = new TeacherAdapterI2B();
	}

	@Override
	public Running adapt(RunningBean s) {
		Running t = new Running();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		t.setYear(Integer.valueOf(s.getCoreInfo().getYearStarted()));
		t.setProject(projectAdapter.adapt(s.getLinkedProject()));
		t.setTeacher(teacherAdapter.adapt(s.getLinkedTeacher()));

		return t;
	}
}
