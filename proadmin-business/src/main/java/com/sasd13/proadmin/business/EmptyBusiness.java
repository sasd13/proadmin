package com.sasd13.proadmin.business;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.exception.BusinessException;

public class EmptyBusiness implements IBusiness<Object> {

	@Override
	public void verify(DAO dao, Object object) throws BusinessException {
		// Do nothing
	}

	@Override
	public void verify(DAO dao, IUpdateWrapper<Object> updateWrapper) throws BusinessException {
		// Do nothing
	}
}
