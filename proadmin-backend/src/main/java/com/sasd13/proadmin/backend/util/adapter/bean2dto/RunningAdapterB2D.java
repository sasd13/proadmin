package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.backend.dao.dto.ProjectDTO;
import com.sasd13.proadmin.backend.dao.dto.RunningDTO;
import com.sasd13.proadmin.backend.dao.dto.TeacherDTO;

public class RunningAdapterB2D implements IAdapter<Running, RunningDTO> {

	@Override
	public RunningDTO adapt(Running s) {
		RunningDTO t = new RunningDTO();

		t.setId(s.getId());
		t.setYear(s.getYear());

		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setId(s.getProject().getId());
		t.setProject(projectDTO);

		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(s.getTeacher().getId());
		t.setTeacher(teacherDTO);

		return t;
	}
}
