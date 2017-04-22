package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.backend.dao.dto.RunningDTO;

public class RunningAdapterD2B implements IAdapter<RunningDTO, Running> {

	private ProjectAdapterD2B projectAdapter;
	private TeacherAdapterD2B teacherAdapter;

	public RunningAdapterD2B() {
		projectAdapter = new ProjectAdapterD2B();
		teacherAdapter = new TeacherAdapterD2B();
	}

	@Override
	public Running adapt(RunningDTO s) {
		Running t = new Running();

		t.setId(s.getId());
		t.setYear(s.getYear());
		t.setProject(projectAdapter.adapt(s.getProject()));
		t.setTeacher(teacherAdapter.adapt(s.getTeacher()));

		return t;
	}
}
