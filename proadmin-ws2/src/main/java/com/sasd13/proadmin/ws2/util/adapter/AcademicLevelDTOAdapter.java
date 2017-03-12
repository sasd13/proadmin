package com.sasd13.proadmin.ws2.util.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.ws2.db.dto.AcademicLevelDTO;

public class AcademicLevelDTOAdapter implements IAdapter<AcademicLevelDTO, AcademicLevel> {

	@Override
	public AcademicLevel adapt(AcademicLevelDTO source) {
		AcademicLevel target = new AcademicLevel();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(AcademicLevelDTO source, AcademicLevel target) {
		target.setCode(source.getCode());
	}
}
