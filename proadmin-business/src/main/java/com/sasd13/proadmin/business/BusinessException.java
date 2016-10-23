package com.sasd13.proadmin.business;

/**
 * Created by Samir on 12/03/2016.
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6941290925336484191L;
	private String title;

	public BusinessException(String title, String message) {
		super(message);

		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
