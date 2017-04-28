package com.sasd13.proadmin.backend.util.adapter.model2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.model.Running;
import com.sasd13.proadmin.itf.bean.LinkedProject;
import com.sasd13.proadmin.itf.bean.LinkedTeacher;
import com.sasd13.proadmin.itf.bean.running.CoreInfo;
import com.sasd13.proadmin.itf.bean.running.Id;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningAdapterM2I implements IAdapter<Running, RunningBean> {

	@Override
	public RunningBean adapt(Running s) {
		RunningBean t = new RunningBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setYearStarted(s.getYear());
		t.setCoreInfo(coreInfo);

		LinkedProject linkedProject = new LinkedProject();
		linkedProject.setId(String.valueOf(s.getProject().getId()));
		t.setLinkedProject(linkedProject);

		LinkedTeacher linkedTeacher = new LinkedTeacher();
		linkedTeacher.setId(String.valueOf(s.getTeacher().getId()));
		t.setLinkedTeacher(linkedTeacher);

		return t;
	}
}
