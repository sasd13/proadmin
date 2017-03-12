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

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.ws2.service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private static final Logger LOGGER = Logger.getLogger(StudentController.class);

	@Autowired
	private IStudentService studentService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Student>> get(@RequestParam(value = "numbers", required = false) List<String> numbers) {
		LOGGER.info("Get");

		try {
			List<Student> students = studentService.read(getParameters(numbers));

			return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<Student>>(HttpStatus.EXPECTATION_FAILED);
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
	public ResponseEntity<Integer> post(@RequestBody Student student) {
		LOGGER.info("Post");

		try {
			studentService.create(student);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Integer> put(@RequestBody List<StudentUpdateWrapper> updateWrappers) {
		LOGGER.info("Put");

		try {
			studentService.update(updateWrappers);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@RequestBody List<Student> students) {
		LOGGER.info("Delete");

		try {
			studentService.delete(students);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}
}
