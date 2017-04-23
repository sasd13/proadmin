package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.level.IAcademicLevel;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws.service.IAcademicLevelService;

public class AcademicLevelService implements IAcademicLevelService {

	private IAcademicLevelDAO academicLevelDAO;

	public AcademicLevelService(DAO dao) {
		academicLevelDAO = (IAcademicLevelDAO) dao.getSession(IAcademicLevelDAO.class);
	}

	@Override
	public List<IAcademicLevel> read(Map<String, String[]> parameters) {
		return academicLevelDAO.read(parameters);
	}

	@Override
	public List<IAcademicLevel> readAll() {
		return academicLevelDAO.readAll();
	}
}
