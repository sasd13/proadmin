package com.sasd13.proadmin.ws2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws2.service.ServiceFactory;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends BeansController<Teacher> {

	private static final Logger LOGGER = Logger.getLogger(TeacherController.class);

	public TeacherController() {
		super();
	}

	@Override
	protected Class<Teacher> getBeanClass() {
		return Teacher.class;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> get(@RequestParam(value = "number", required = false) String number, @RequestParam(value = "email", required = false) String email) {
		LOGGER.info("Get");

		try {
			List<Teacher> teachers = ServiceFactory.make(getBeanClass()).read(getParameters(number, email));

			return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
		} catch (ServiceException e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<Teacher>>(HttpStatus.EXPECTATION_FAILED);
	}

	private Map<String, String[]> getParameters(String number, String email) {
		Map<String, String[]> parameters = new HashMap<>();

		if (number != null) {
			parameters.put(EnumParameter.NUMBER.getName(), new String[] { number });
		}

		if (email != null) {
			parameters.put(EnumParameter.EMAIL.getName(), new String[] { email });
		}

		return parameters;
	}
}
