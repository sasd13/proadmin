package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.AcademicLevel;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;
import com.sasd13.proadmin.itf.bean.academiclevel.CoreInfo;

public class AcademicLevelAdapterB2I implements IAdapter<AcademicLevel, AcademicLevelBean> {

	@Override
	public AcademicLevelBean adapt(AcademicLevel s) {
		AcademicLevelBean t = new AcademicLevelBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setCode(s.getCode());
		t.setCoreInfo(coreInfo);

		return t;
	}
}
