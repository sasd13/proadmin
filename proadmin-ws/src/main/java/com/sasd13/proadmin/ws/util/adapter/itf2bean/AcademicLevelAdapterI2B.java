package com.sasd13.proadmin.ws.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;
import com.sasd13.proadmin.ws.bean.AcademicLevel;

public class AcademicLevelAdapterI2B implements IAdapter<AcademicLevelBean, AcademicLevel> {

	@Override
	public AcademicLevel adapt(AcademicLevelBean s) {
		AcademicLevel t = new AcademicLevel();

		t.setCode(s.getCoreInfo().getCode());

		return t;
	}
}
