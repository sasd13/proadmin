package com.sasd13.proadmin.ws2.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.AcademicLevelUpdateWrapper;

public class AcademicLevelBusiness implements IBusiness<AcademicLevel> {

	@Autowired
	private IAcademicLevelDAO dao;

	private Map<String, String[]> parameters;

	public AcademicLevelBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(AcademicLevel academicLevel) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { academicLevel.getCode() });

		if (!dao.select(parameters).isEmpty()) {
			throw new BusinessException("AcademicLevel already exist");
		}
	}

	@Override
	public void verify(IUpdateWrapper<AcademicLevel> updateWrapper) throws BusinessException {
		AcademicLevelUpdateWrapper academicLevelUpdateWrapper = (AcademicLevelUpdateWrapper) updateWrapper;

		if (!academicLevelUpdateWrapper.getCode().equals(academicLevelUpdateWrapper.getWrapped().getCode())) {
			verify(academicLevelUpdateWrapper.getWrapped());
		}
	}
}
