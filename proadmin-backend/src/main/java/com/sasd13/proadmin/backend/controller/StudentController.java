package com.sasd13.proadmin.backend.controller;

import java.util.ArrayList;
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
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.student.StudentBean;

@RestController
@RequestMapping("/students")
public class StudentController extends Controller {

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
	public ResponseEntity<ResponseBean> search(@RequestParam(value = "intermediary", required = true) String intermediary) {
		LOGGER.info("[Proadmin-Backend] Student : read");

		try {
			Student result = studentService.read(intermediary);
			ResponseBean responseBean = new ResponseBean();
			responseBean.getContext().setPaginationTotalItems(String.valueOf(1));
			responseBean.setData(new StudentAdapterB2I().adapt(result));

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] Student : search");

		try {
			List<Student> results = studentService.read(searchBean.getCriterias());
			ResponseBean responseBean = new ResponseBean();
			List<StudentBean> list = new ArrayList<>();
			StudentAdapterB2I adapter = new StudentAdapterB2I();

			for (Student result : results) {
				list.add(adapter.adapt(result));
			}

			addHeaders(searchBean, responseBean);
			responseBean.getContext().setPaginationTotalItems(String.valueOf(list.size()));
			responseBean.setData(list);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
