package com.sasd13.proadmin.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.backend.model.AcademicLevel;
import com.sasd13.proadmin.backend.service.IAcademicLevelService;
import com.sasd13.proadmin.backend.util.adapter.itf.AcademicLevelITFAdapter;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AcademicLevelService implements IAcademicLevelService {

	@Autowired
	private IAcademicLevelDAO academicLevelDAO;

	private AcademicLevelITFAdapter adapter;

	public AcademicLevelService() {
		adapter = new AcademicLevelITFAdapter();
	}

	@Override
	public List<AcademicLevelBean> readAll() {
		List<AcademicLevel> academicLevels = academicLevelDAO.readAll();

		return adapter.adaptM2I(academicLevels);
	}
}
