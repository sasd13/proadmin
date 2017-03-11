package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.util.wrapper.update.AcademicLevelUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.AcademicLevelDTO;

public interface IAcademicLevelDAO {

	String TABLE = "academicLevels";
	String COLUMN_CODE = "_code";

	AcademicLevelDTO insert(AcademicLevel academicLevel);

	void update(AcademicLevelUpdateWrapper updateWrapper);

	void delete(AcademicLevel academicLevel);

	AcademicLevelDTO select(String code);

	List<AcademicLevelDTO> selectAll();
}
