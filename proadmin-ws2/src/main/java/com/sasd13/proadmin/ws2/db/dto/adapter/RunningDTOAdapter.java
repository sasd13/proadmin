package com.sasd13.proadmin.ws2.db.dto.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.ws2.db.dto.RunningDTO;

public class RunningDTOAdapter implements IAdapter<RunningDTO, Running> {

	@Override
	public Running adapt(RunningDTO source) {
		Running target = new Running();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(RunningDTO source, Running target) {
		target.setYear(source.getYear());
		target.setProject(new ProjectDTOAdapter().adapt(source.getProject()));
		target.setTeacher(new TeacherDTOAdapter().adapt(source.getTeacher()));
	}
}
