package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;

import com.sasd13.proadmin.ws2.db.dto.AcademicLevelDTO;

public interface IAcademicLevelDAO {

	List<AcademicLevelDTO> readAll();
}
