package com.sasd13.proadmin.ws.service;

import com.sasd13.proadmin.ws.WSException;

public interface IManageService<T> {

	long create(T t) throws WSException;

	void update(T t) throws WSException;

	void delete(T t) throws WSException;
}
