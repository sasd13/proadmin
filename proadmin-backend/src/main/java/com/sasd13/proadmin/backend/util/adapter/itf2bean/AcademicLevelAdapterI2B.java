package com.sasd13.proadmin.backend.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.AcademicLevel;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;

public class AcademicLevelAdapterI2B implements IAdapter<AcademicLevelBean, AcademicLevel> {

	@Override
	public AcademicLevel adapt(AcademicLevelBean s) {
		AcademicLevel t = new AcademicLevel();

		t.setId(Integer.valueOf(s.getId().getId()));
		t.setCode(s.getCoreInfo().getCode());

		return t;
	}
}