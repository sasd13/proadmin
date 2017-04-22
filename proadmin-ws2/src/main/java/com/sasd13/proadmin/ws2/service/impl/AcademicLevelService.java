package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.level.AcademicLevel;
import com.sasd13.proadmin.ws2.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws2.dao.dto.AcademicLevelDTO;
import com.sasd13.proadmin.ws2.service.IAcademicLevelService;
import com.sasd13.proadmin.ws2.util.adapter.dto2bean.AcademicLevelDTOAdapter;

@Service
public class AcademicLevelService implements IAcademicLevelService {

	@Autowired
	private IAcademicLevelDAO academicLevelDAO;

	@Override
	public List<AcademicLevel> readAll() {
		List<AcademicLevel> academicLevels = new ArrayList<>();

		List<AcademicLevelDTO> dtos = academicLevelDAO.readAll();
		AcademicLevelDTOAdapter adapter = new AcademicLevelDTOAdapter();

		for (AcademicLevelDTO dto : dtos) {
			academicLevels.add(adapter.adapt(dto));
		}

		return academicLevels;
	}
}
