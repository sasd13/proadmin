package com.sasd13.proadmin.ws.service;

public interface IReadService<T> {

	T read(T t);

	T deepRead(T t);
}
