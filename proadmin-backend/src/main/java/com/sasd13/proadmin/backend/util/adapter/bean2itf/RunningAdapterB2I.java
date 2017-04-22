package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.running.CoreInfo;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningAdapterB2I implements IAdapter<Running, RunningBean> {

	private ProjectAdapterB2I projectAdapter;
	private TeacherAdapterB2I teacherAdapter;

	public RunningAdapterB2I() {
		projectAdapter = new ProjectAdapterB2I();
		teacherAdapter = new TeacherAdapterB2I();
	}

	@Override
	public RunningBean adapt(Running s) {
		RunningBean t = new RunningBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setYearStarted(String.valueOf(s.getYear()));
		coreInfo.setLinkedProject(projectAdapter.adapt(s.getProject()));
		coreInfo.setLinkedTeacher(teacherAdapter.adapt(s.getTeacher()));
		t.setCoreInfo(coreInfo);

		return t;
	}
}
