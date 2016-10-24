package com.sasd13.proadmin.business;

import com.sasd13.javaex.dao.ILayeredDAO;
import com.sasd13.javaex.dao.LayeredPersistor;

public abstract class BusinessService<T> {

	protected LayeredPersistor persistor;

	protected BusinessService(ILayeredDAO layeredDAO) {
		persistor = new LayeredPersistor(layeredDAO);
	}
}
