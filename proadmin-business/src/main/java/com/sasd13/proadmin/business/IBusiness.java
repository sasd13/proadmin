package com.sasd13.proadmin.business;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.exception.BusinessException;

public interface IBusiness<T> {

	void verify(DAO dao, T t) throws BusinessException;

	void verify(DAO dao, IUpdateWrapper<T> updateWrapper) throws BusinessException;
}
