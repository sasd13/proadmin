package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedInfo;
import com.sasd13.proadmin.itf.bean.running.CoreInfo;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningAdapterB2I implements IAdapter<Running, RunningBean> {

	@Override
	public RunningBean adapt(Running s) {
		RunningBean t = new RunningBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setYearStarted(String.valueOf(s.getYear()));
		t.setCoreInfo(coreInfo);

		LinkedInfo linkedProject = new LinkedInfo();
		linkedProject.setId(String.valueOf(s.getProject().getId()));
		t.setLinkedProject(linkedProject);

		LinkedInfo linkedTeacher = new LinkedInfo();
		linkedTeacher.setId(String.valueOf(s.getTeacher().getId()));
		t.setLinkedTeacher(linkedTeacher);

		return t;
	}
}
