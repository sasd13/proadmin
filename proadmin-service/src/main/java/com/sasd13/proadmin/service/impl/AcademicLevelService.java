package com.sasd13.proadmin.service.impl;

import java.util.List;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.service.IAcademicLevelService;

public class AcademicLevelService implements IAcademicLevelService {

	private IAcademicLevelDAO session;

	public AcademicLevelService(DAO dao) {
		session = (IAcademicLevelDAO) dao.getSession(IAcademicLevelDAO.class);
	}

	@Override
	public List<AcademicLevel> readAll() {
		return session.readAll();
	}
}
