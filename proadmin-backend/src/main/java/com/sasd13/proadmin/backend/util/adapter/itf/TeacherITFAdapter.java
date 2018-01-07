package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.Teacher;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.TeacherAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.TeacherAdapterM2I;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public class TeacherITFAdapter extends ITFAdapter<Teacher, TeacherBean> {

	public TeacherITFAdapter() {
		super(new TeacherAdapterI2M(), new TeacherAdapterM2I());
	}
}
