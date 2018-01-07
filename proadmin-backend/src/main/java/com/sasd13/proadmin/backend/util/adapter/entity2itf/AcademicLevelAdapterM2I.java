package com.sasd13.proadmin.backend.util.adapter.entity2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.entity.AcademicLevel;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;
import com.sasd13.proadmin.itf.bean.academiclevel.CoreInfo;

public class AcademicLevelAdapterM2I implements IAdapter<AcademicLevel, AcademicLevelBean> {

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
