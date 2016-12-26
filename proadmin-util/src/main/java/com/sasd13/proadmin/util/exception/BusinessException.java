package com.sasd13.proadmin.util.exception;

/**
 * Created by Samir on 12/03/2016.
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6941290925336484191L;

	public BusinessException(String message) {
		super(message);
	}
}
