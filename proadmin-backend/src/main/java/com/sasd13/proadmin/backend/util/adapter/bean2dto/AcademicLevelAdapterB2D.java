package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.AcademicLevel;
import com.sasd13.proadmin.backend.dao.dto.AcademicLevelDTO;

public class AcademicLevelAdapterB2D implements IAdapter<AcademicLevel, AcademicLevelDTO> {

	@Override
	public AcademicLevelDTO adapt(AcademicLevel s) {
		AcademicLevelDTO t = new AcademicLevelDTO();

		t.setId(s.getId());
		t.setCode(s.getCode());

		return t;
	}
}
