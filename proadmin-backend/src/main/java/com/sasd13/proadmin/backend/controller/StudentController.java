package com.sasd13.proadmin.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.model.Student;
import com.sasd13.proadmin.backend.service.IStudentService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.StudentAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.StudentAdapterM2I;
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
			studentService.create(new StudentAdapterI2M().adapt(requestBean.getData().get(0)));

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
			studentService.update(new StudentAdapterI2M().adapt(requestBean.getData().get(0)));

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
			studentService.delete(new StudentAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/read", method = RequestMethod.GET)
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<ResponseBean> search(@RequestParam(value = "intermediary", required = true) String intermediary) {
		LOGGER.info("[Proadmin-Backend] Student : read");

		try {
			Student result = studentService.read(intermediary);
			StudentResponseBean responseBean = new StudentResponseBean();
			List<StudentBean> list = new ArrayList<>();

			list.add(new StudentAdapterM2I().adapt(result));
			responseBean.setData(list);
			responseBean.getHeader().getApplicativeContext().setPaginationTotalItems(String.valueOf(1));

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] Student : search");

		try {
			List<Student> results = studentService.read(searchBean.getCriterias());
			StudentResponseBean responseBean = new StudentResponseBean();
			List<StudentBean> list = new ArrayList<>();
			StudentAdapterM2I adapter = new StudentAdapterM2I();

			for (Student result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(responseBean, list.size(), searchBean);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
