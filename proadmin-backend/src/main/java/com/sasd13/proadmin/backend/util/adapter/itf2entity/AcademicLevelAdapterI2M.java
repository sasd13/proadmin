package com.sasd13.proadmin.backend.util.adapter.itf2entity;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.entity.AcademicLevel;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;

public class AcademicLevelAdapterI2M implements IAdapter<AcademicLevelBean, AcademicLevel> {

	@Override
	public AcademicLevel adapt(AcademicLevelBean s) {
		AcademicLevel t = new AcademicLevel();

		t.setId(Integer.valueOf(s.getId().getId()));
		t.setCode(s.getCoreInfo().getCode());

		return t;
	}
}
