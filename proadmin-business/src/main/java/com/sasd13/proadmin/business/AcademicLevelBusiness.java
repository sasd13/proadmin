package com.sasd13.proadmin.business;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.AcademicLevelUpdateWrapper;

public class AcademicLevelBusiness implements IBusiness<AcademicLevel> {

	private Map<String, String[]> parameters;

	public AcademicLevelBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(DAO dao, AcademicLevel academicLevel) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { academicLevel.getCode() });

		if (!dao.getSession(AcademicLevel.class).select(parameters).isEmpty()) {
			throw new BusinessException("AcademicLevel already exist");
		}
	}
	
	@Override
	public void verify(DAO dao, IUpdateWrapper<AcademicLevel> updateWrapper) throws BusinessException {
		AcademicLevelUpdateWrapper academicLevelUpdateWrapper = (AcademicLevelUpdateWrapper) updateWrapper;
		
		if (!academicLevelUpdateWrapper.getCode().equals(academicLevelUpdateWrapper.getWrapped().getCode())) {
			verify(dao, academicLevelUpdateWrapper.getWrapped());
		}
	}
}
