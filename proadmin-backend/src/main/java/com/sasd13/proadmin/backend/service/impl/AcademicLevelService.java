package com.sasd13.proadmin.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.backend.model.AcademicLevel;
import com.sasd13.proadmin.backend.service.IAcademicLevelService;

@Service
public class AcademicLevelService implements IAcademicLevelService {

	@Autowired
	private IAcademicLevelDAO academicLevelDAO;

	@Override
	public List<AcademicLevel> readAll() {
		return academicLevelDAO.readAll();
	}
}
