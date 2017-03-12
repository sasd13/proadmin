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

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.ws2.service.ITeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	private static final Logger LOGGER = Logger.getLogger(TeacherController.class);

	@Autowired
	private ITeacherService teacherService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> get(@RequestParam(value = "numbers", required = false) List<String> numbers) {
		LOGGER.info("Get");

		try {
			List<Teacher> teachers = teacherService.read(getParameters(numbers));

			return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<Teacher>>(HttpStatus.EXPECTATION_FAILED);
	}

	private Map<String, String[]> getParameters(List<String> numbers) {
		Map<String, String[]> parameters = new HashMap<>();

		if (numbers != null) {
			for (String number : numbers) {
				parameters.put(EnumParameter.NUMBER.getName(), new String[] { number });
			}
		}

		return parameters;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> post(@RequestBody Teacher teacher) {
		LOGGER.info("Post");

		try {
			teacherService.create(teacher);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Integer> put(@RequestBody List<TeacherUpdateWrapper> updateWrappers) {
		LOGGER.info("Put");

		try {
			teacherService.update(updateWrappers);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@RequestBody List<Teacher> teachers) {
		LOGGER.info("Delete");

		try {
			teacherService.delete(teachers);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}
}
