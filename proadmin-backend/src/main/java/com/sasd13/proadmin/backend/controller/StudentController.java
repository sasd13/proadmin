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

import com.sasd13.proadmin.backend.service.IStudentService;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.student.StudentBean;
import com.sasd13.proadmin.itf.bean.student.StudentRequestBean;
import com.sasd13.proadmin.itf.bean.student.StudentResponseBean;

@RestController
@RequestMapping("/students")
public class StudentController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(StudentController.class);

	@Autowired
	private IStudentService studentService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody StudentRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Student : create");

		try {
			studentService.create(requestBean.getData().get(0));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody StudentRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Student : update");

		try {
			studentService.update(requestBean.getData().get(0));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody StudentRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Student : delete");

		try {
			studentService.delete(requestBean.getData().get(0));

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
			StudentBean result = studentService.read(intermediary);
			StudentResponseBean responseBean = new StudentResponseBean();
			List<StudentBean> results = new ArrayList<>();
			int totalItem = 0;

			if (result != null) {
				results.add(result);
				totalItem = 1;
			}

			responseBean.setData(results);
			responseBean.getHeader().getApplicativeContext().setPaginationTotalItems(String.valueOf(totalItem));

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
			List<StudentBean> results = studentService.read(searchBean.getCriterias());
			StudentResponseBean responseBean = new StudentResponseBean();

			responseBean.setData(results);
			addHeaders(responseBean, results.size(), searchBean);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
