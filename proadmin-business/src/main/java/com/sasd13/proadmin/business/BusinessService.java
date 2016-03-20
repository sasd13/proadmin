package com.sasd13.proadmin.business;

import com.sasd13.javaex.db.IDAO;
import com.sasd13.javaex.db.Persistence;

public abstract class BusinessService<T> {
	
	protected Persistence persistence;
	
	protected BusinessService(IDAO dao) {
		persistence = new Persistence(dao);
	}
}
