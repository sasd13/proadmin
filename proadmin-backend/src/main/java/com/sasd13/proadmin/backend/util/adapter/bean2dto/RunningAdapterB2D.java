package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.backend.dao.dto.RunningDTO;

public class RunningAdapterB2D implements IAdapter<Running, RunningDTO> {

	private ProjectAdapterB2D projectAdapter;
	private TeacherAdapterB2D teacherAdapter;

	public RunningAdapterB2D() {
		projectAdapter = new ProjectAdapterB2D();
		teacherAdapter = new TeacherAdapterB2D();
	}

	@Override
	public RunningDTO adapt(Running s) {
		RunningDTO t = new RunningDTO();

		t.setId(s.getId());
		t.setYear(s.getYear());
		t.setProject(projectAdapter.adapt(s.getProject()));
		t.setTeacher(teacherAdapter.adapt(s.getTeacher()));

		return t;
	}
}
