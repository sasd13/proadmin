package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.level.IAcademicLevel;

public interface IAcademicLevelService {

	List<IAcademicLevel> read(Map<String, String[]> parameters);

	List<IAcademicLevel> readAll();
}
