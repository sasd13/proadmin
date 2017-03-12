package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.ws2.db.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws2.db.dto.AcademicLevelDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.AcademicLevelDTOAdapter;
import com.sasd13.proadmin.ws2.service.IAcademicLevelService;

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
