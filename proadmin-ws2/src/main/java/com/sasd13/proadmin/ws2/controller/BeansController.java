package com.sasd13.proadmin.ws2.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.validator.UpdateWrapperValidatorFactory;
import com.sasd13.proadmin.util.validator.ValidatorFactory;
import com.sasd13.proadmin.ws2.business.BusinessFactory;
import com.sasd13.proadmin.ws2.business.IBusiness;
import com.sasd13.proadmin.ws2.service.ServiceFactory;

@RestController
public abstract class BeansController<T> {

	private static final Logger LOGGER = Logger.getLogger(BeansController.class);

	private IValidator<T> validator;
	private IValidator<IUpdateWrapper<T>> updateWrapperValidator;
	private IBusiness<T> business;

	@SuppressWarnings("unchecked")
	public BeansController() {
		super();

		try {
			validator = ValidatorFactory.make(getBeanClass());
			updateWrapperValidator = (IValidator<IUpdateWrapper<T>>) UpdateWrapperValidatorFactory.make(getBeanClass());
			business = BusinessFactory.make(getBeanClass());
		} catch (ValidatorException | ServiceException | BusinessException e) {
			LOGGER.error(e);
		}
	}

	protected abstract Class<T> getBeanClass();

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> post(@RequestBody T t) {
		LOGGER.info("Post");

		try {
			validator.validate(t);
			business.verify(t);
			ServiceFactory.make(getBeanClass()).create(t);

			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> put(@RequestBody IUpdateWrapper<T> updateWrapper) {
		LOGGER.info("Put");

		try {
			updateWrapperValidator.validate(updateWrapper);
			business.verify(updateWrapper);
			ServiceFactory.make(getBeanClass()).update(updateWrapper);

			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> delete(@RequestBody T t) {
		LOGGER.info("Delete");

		try {
			validator.validate(t);
			ServiceFactory.make(getBeanClass()).delete(t);

			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
	}
}
