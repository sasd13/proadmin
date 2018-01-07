package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.backend.entity.AcademicLevel;
import com.sasd13.proadmin.backend.service.IAcademicLevelService;
import com.sasd13.proadmin.backend.util.adapter.entity2itf.AcademicLevelAdapterM2I;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AcademicLevelService implements IAcademicLevelService {

	@Autowired
	private IAcademicLevelDAO academicLevelDAO;

	@Override
	public List<AcademicLevelBean> readAll() {
		List<AcademicLevel> academicLevels = academicLevelDAO.readAll();

		return adaptM2I(academicLevels);
	}

	private List<AcademicLevelBean> adaptM2I(List<AcademicLevel> academicLevels) {
		List<AcademicLevelBean> list = new ArrayList<>();
		AcademicLevelAdapterM2I adapter = new AcademicLevelAdapterM2I();

		for (AcademicLevel academicLevel : academicLevels) {
			list.add(adapter.adapt(academicLevel));
		}

		return list;
	}
}
