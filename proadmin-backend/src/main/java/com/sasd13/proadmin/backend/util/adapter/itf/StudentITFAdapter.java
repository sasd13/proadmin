package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.Student;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.StudentAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.StudentAdapterM2I;
import com.sasd13.proadmin.itf.bean.student.StudentBean;

public class StudentITFAdapter extends ITFAdapter<Student, StudentBean> {

	public StudentITFAdapter() {
		super(new StudentAdapterI2M(), new StudentAdapterM2I());
	}
}
