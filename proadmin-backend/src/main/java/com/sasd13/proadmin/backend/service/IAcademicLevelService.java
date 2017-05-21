package com.sasd13.proadmin.backend.service;

import java.util.List;

import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;

public interface IAcademicLevelService {

	List<AcademicLevelBean> readAll();
}
