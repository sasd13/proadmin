package com.sasd13.proadmin.ws2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws2.service.ITeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	private static final Logger LOG = Logger.getLogger(TeacherController.class);

	@Autowired
	private ITeacherService teacherService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> get(
			@RequestParam(value = "number", required = false) String[] numbers, 
			@RequestParam(value = "email", required = false) String[] emails) {
		LOG.info("Get");
		
		try {
			List<Teacher> teachers = teacherService.read(getParameters(numbers, emails));
			
			return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e);
		}

		return new ResponseEntity<List<Teacher>>(HttpStatus.EXPECTATION_FAILED);
	}

	private Map<String, String[]> getParameters(String[] numbers, String[] emails) {
		Map<String, String[]> parameters = new HashMap<>();

		if (numbers != null) {
			parameters.put(EnumParameter.NUMBER.getName(), numbers);
		}

		if (emails != null) {
			parameters.put(EnumParameter.EMAIL.getName(), emails);
		}

		return parameters;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> post(@RequestBody Teacher teacher) {
		LOG.info("Post : teacher=" + String.valueOf(teacher.getNumber()));
		
		try {
			teacherService.create(teacher);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> put(
			@RequestParam(value = "number") String number, 
			@RequestBody Teacher teacher) {
		LOG.info("Put : teacher=" + String.valueOf(number));
		
		try {
			teacherService.update(number, teacher);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> delete(@RequestParam(value = "number") String number) {
		LOG.info("Delete : teacher=" + String.valueOf(number));
		
		try {
			teacherService.delete(number);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
	}
}
