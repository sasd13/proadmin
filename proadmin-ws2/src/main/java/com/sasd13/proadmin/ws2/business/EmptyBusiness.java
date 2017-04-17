package com.sasd13.proadmin.ws2.business;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.util.error.BusinessException;

public class EmptyBusiness implements IBusiness<Object> {

	@Override
	public void verify(Object object) throws BusinessException {
		// Do nothing
	}

	@Override
	public void verify(IUpdateWrapper<Object> updateWrapper) throws BusinessException {
		// Do nothing
	}
}
