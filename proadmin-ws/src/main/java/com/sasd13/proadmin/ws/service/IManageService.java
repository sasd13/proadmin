package com.sasd13.proadmin.ws.service;

public interface IManageService<T> {
	
	long create(T t);
	
	void update(T t);
	
	void delete(T t);
}
