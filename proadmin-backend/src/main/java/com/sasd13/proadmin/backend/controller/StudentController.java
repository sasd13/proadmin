package com.sasd13.proadmin.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.bean.Student;
import com.sasd13.proadmin.backend.service.IStudentService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.StudentAdapterB2I;
import com.sasd13.proadmin.backend.util.adapter.itf2bean.StudentAdapterI2B;
import com.sasd13.proadmin.itf.RequestBean;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.student.StudentBean;

@RestController
@RequestMapping("/students")
public class StudentController {

	private static final Logger LOGGER = Logger.getLogger(StudentController.class);

	@Autowired
	private IStudentService studentService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody StudentBean studentBean) {
		LOGGER.info("[Proadmin-Backend] Student : create");

		try {
			studentService.create(new StudentAdapterI2B().adapt(studentBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody StudentBean studentBean) {
		LOGGER.info("[Proadmin-Backend] Student : update");

		try {
			studentService.update(new StudentAdapterI2B().adapt(studentBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody StudentBean studentBean) {
		LOGGER.info("[Proadmin-Backend] Student : delete");

		try {
			studentService.delete(new StudentAdapterI2B().adapt(studentBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/read", method = RequestMethod.GET)
	public ResponseEntity<StudentBean> search(@RequestParam(value = "intermediary", required = true) String intermediary) {
		LOGGER.info("[Proadmin-Backend] Student : read");

		try {
			Student result = studentService.read(intermediary);
			StudentBean response = new StudentAdapterB2I().adapt(result);

			return new ResponseEntity<StudentBean>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<StudentBean>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean<StudentBean>> search(@RequestBody RequestBean request) {
		LOGGER.info("[Proadmin-Backend] Student : search");

		try {
			List<Student> results = studentService.read(request.getCriteria());
			ResponseBean<StudentBean> response = new ResponseBean<>();
			StudentAdapterB2I adapter = new StudentAdapterB2I();

			for (Student result : results) {
				response.getData().add(adapter.adapt(result));
			}

			return new ResponseEntity<ResponseBean<StudentBean>>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean<StudentBean>>(HttpStatus.EXPECTATION_FAILED);
	}
}
