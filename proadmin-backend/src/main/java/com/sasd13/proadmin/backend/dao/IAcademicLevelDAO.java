package com.sasd13.proadmin.backend.dao;

import java.util.List;

import com.sasd13.proadmin.backend.dao.dto.AcademicLevelDTO;

public interface IAcademicLevelDAO {

	List<AcademicLevelDTO> readAll();
}
