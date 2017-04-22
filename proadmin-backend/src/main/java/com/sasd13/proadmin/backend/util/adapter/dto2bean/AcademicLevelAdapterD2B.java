package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.AcademicLevel;
import com.sasd13.proadmin.backend.dao.dto.AcademicLevelDTO;

public class AcademicLevelAdapterD2B implements IAdapter<AcademicLevelDTO, AcademicLevel> {

	@Override
	public AcademicLevel adapt(AcademicLevelDTO s) {
		AcademicLevel t = new AcademicLevel();

		t.setId(s.getId());
		t.setCode(s.getCode());

		return t;
	}
}
