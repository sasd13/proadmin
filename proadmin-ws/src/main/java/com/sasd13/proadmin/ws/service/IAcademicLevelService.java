package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.level.AcademicLevel;

public interface IAcademicLevelService {

	List<AcademicLevel> read(Map<String, String[]> parameters);

	List<AcademicLevel> readAll();
}
