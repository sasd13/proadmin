package com.sasd13.proadmin.ws.service.impl;

import java.util.List;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws.service.IAcademicLevelService;

public class AcademicLevelService implements IAcademicLevelService {

	private IAcademicLevelDAO academicLevelDAO;

	public AcademicLevelService(DAO dao) {
		academicLevelDAO = (IAcademicLevelDAO) dao.getSession(IAcademicLevelDAO.class);
	}

	@Override
	public List<AcademicLevel> readAll() {
		return academicLevelDAO.readAll();
	}
}
