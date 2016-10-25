package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.WSException;

public interface IReadService<T> {

	List<T> read(Map<String, String[]> parameters) throws WSException;

	List<T> readAll() throws WSException;

	List<T> deepRead(Map<String, String[]> parameters) throws WSException;

	List<T> deepReadAll() throws WSException;
}
