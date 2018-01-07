package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.AcademicLevel;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.AcademicLevelAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.AcademicLevelAdapterM2I;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;

public class AcademicLevelITFAdapter extends ITFAdapter<AcademicLevel, AcademicLevelBean> {

	public AcademicLevelITFAdapter() {
		super(new AcademicLevelAdapterI2M(), new AcademicLevelAdapterM2I());
	}
}
