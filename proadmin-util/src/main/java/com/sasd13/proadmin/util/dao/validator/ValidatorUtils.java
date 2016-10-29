package com.sasd13.proadmin.util.dao.validator;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.validator.ValidatorException;

public class ValidatorUtils {

	private static final Logger LOG = Logger.getLogger(ValidatorUtils.class);

	@SuppressWarnings("unchecked")
	public static <T> void validate(T t) throws DAOException {
		try {
			ValidatorFactory.make((Class<T>) t.getClass()).validate(t);
		} catch (ValidatorException e) {
			LOG.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}
