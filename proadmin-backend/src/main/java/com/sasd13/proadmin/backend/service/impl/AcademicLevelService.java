package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.AcademicLevel;
import com.sasd13.proadmin.backend.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.backend.dao.dto.AcademicLevelDTO;
import com.sasd13.proadmin.backend.service.IAcademicLevelService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.AcademicLevelAdapterD2B;

@Service
public class AcademicLevelService implements IAcademicLevelService {

	@Autowired
	private IAcademicLevelDAO academicLevelDAO;

	@Override
	public List<AcademicLevel> readAll() {
		List<AcademicLevel> list = new ArrayList<>();

		List<AcademicLevelDTO> dtos = academicLevelDAO.readAll();
		AcademicLevelAdapterD2B adapter = new AcademicLevelAdapterD2B();

		for (AcademicLevelDTO dto : dtos) {
			list.add(adapter.adapt(dto));
		}

		return list;
	}
}
