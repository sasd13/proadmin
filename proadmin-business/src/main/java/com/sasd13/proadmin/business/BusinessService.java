package com.sasd13.proadmin.business;

import com.sasd13.javaex.db.ILayeredDAO;
import com.sasd13.javaex.db.LayeredPersistor;

public abstract class BusinessService<T> {
	
	protected LayeredPersistor persistor;
	
	protected BusinessService(ILayeredDAO layeredDAO) {
		persistor = new LayeredPersistor(layeredDAO);
	}
}
