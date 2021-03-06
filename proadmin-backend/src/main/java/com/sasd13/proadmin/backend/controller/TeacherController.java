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

import com.sasd13.proadmin.backend.service.ITeacherService;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherRequestBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherResponseBean;

@RestController
@RequestMapping("/teachers")
public class TeacherController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(TeacherController.class);

	@Autowired
	private ITeacherService teacherService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody TeacherRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Teacher : create");

		try {
			teacherService.create(requestBean.getData().get(0));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody TeacherRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Teacher : update");

		try {
			teacherService.update(requestBean.getData().get(0));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody TeacherRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Teacher : delete");

		try {
			teacherService.delete(requestBean.getData().get(0));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/read", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> search(@RequestParam(value = "intermediary", required = true) String intermediary) {
		LOGGER.info("[Proadmin-Backend] Teacher : read");

		try {
			TeacherBean result = teacherService.read(intermediary);
			TeacherResponseBean responseBean = new TeacherResponseBean();
			List<TeacherBean> results = new ArrayList<>();

			if (result != null) {
				results.add(result);
			}

			responseBean.setData(results);
			responseBean.getHeader().getApplicativeContext().setPaginationTotalItems(String.valueOf(results.size()));

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] Teacher : search");

		try {
			List<TeacherBean> results = teacherService.read(searchBean.getCriterias());
			TeacherResponseBean responseBean = new TeacherResponseBean();

			responseBean.setData(results);
			addHeaders(responseBean, results.size(), searchBean);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
