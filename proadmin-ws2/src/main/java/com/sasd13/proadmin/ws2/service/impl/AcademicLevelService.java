package com.sasd13.proadmin.ws2.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.ws2.db.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws2.db.dto.AcademicLevelDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.AcademicLevelDTOAdapter;
import com.sasd13.proadmin.ws2.service.IAcademicLevelService;

@Service
public class AcademicLevelService implements IAcademicLevelService {

	private static final Logger LOGGER = Logger.getLogger(AcademicLevel.class);

	@Autowired
	private IAcademicLevelDAO academicLevelDAO;

	@Override
	public List<AcademicLevel> readAll() {
		LOGGER.info("readAll");

		List<AcademicLevel> academicLevels = new ArrayList<>();
		List<AcademicLevelDTO> results = academicLevelDAO.readAll();
		AcademicLevelDTOAdapter adapter = new AcademicLevelDTOAdapter();

		for (Serializable result : results) {
			academicLevels.add(adapter.adapt((AcademicLevelDTO) result));
		}

		return academicLevels;
	}
}
