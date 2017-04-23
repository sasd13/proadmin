package com.sasd13.proadmin.ws.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.student.CoreInfo;
import com.sasd13.proadmin.itf.bean.student.StudentBean;
import com.sasd13.proadmin.ws.bean.Student;

public class StudentAdapterB2I implements IAdapter<Student, StudentBean> {

	@Override
	public StudentBean adapt(Student s) {
		StudentBean t = new StudentBean();

		Id id = new Id();
		id.setId(s.getIntermediary());
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setIntermediary(s.getIntermediary());
		coreInfo.setFirstName(s.getFirstName());
		coreInfo.setLastName(s.getLastName());
		coreInfo.setEmail(s.getEmail());
		t.setCoreInfo(coreInfo);

		return t;
	}
}
