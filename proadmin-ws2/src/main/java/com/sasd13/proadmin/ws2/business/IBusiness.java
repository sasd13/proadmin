package com.sasd13.proadmin.ws2.business;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.util.error.BusinessException;

public interface IBusiness<T> {

	void verify(T t) throws BusinessException;

	void verify(IUpdateWrapper<T> updateWrapper) throws BusinessException;
}
