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

import com.sasd13.proadmin.backend.bean.Teacher;
import com.sasd13.proadmin.backend.service.ITeacherService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.TeacherAdapterB2I;
import com.sasd13.proadmin.backend.util.adapter.itf2bean.TeacherAdapterI2B;
import com.sasd13.proadmin.itf.RequestBean;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	private static final Logger LOGGER = Logger.getLogger(TeacherController.class);

	@Autowired
	private ITeacherService teacherService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody TeacherBean teacherBean) {
		LOGGER.info("[Proadmin-Backend] Teacher : create");

		try {
			teacherService.create(new TeacherAdapterI2B().adapt(teacherBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody TeacherBean teacherBean) {
		LOGGER.info("[Proadmin-Backend] Teacher : update");

		try {
			teacherService.update(new TeacherAdapterI2B().adapt(teacherBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody TeacherBean teacherBean) {
		LOGGER.info("[Proadmin-Backend] Teacher : delete");

		try {
			teacherService.delete(new TeacherAdapterI2B().adapt(teacherBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/read", method = RequestMethod.GET)
	public ResponseEntity<TeacherBean> search(@RequestParam(value = "intermediary", required = true) String intermediary) {
		LOGGER.info("[Proadmin-Backend] Teacher : read");

		try {
			Teacher result = teacherService.read(intermediary);
			TeacherBean response = new TeacherAdapterB2I().adapt(result);

			return new ResponseEntity<TeacherBean>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<TeacherBean>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean<TeacherBean>> search(@RequestBody RequestBean request) {
		LOGGER.info("[Proadmin-Backend] Teacher : search");

		try {
			List<Teacher> results = teacherService.read(request.getCriteria());
			ResponseBean<TeacherBean> response = new ResponseBean<TeacherBean>();
			TeacherAdapterB2I adapter = new TeacherAdapterB2I();

			for (Teacher result : results) {
				response.getData().add(adapter.adapt(result));
			}

			return new ResponseEntity<ResponseBean<TeacherBean>>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean<TeacherBean>>(HttpStatus.EXPECTATION_FAILED);
	}
}
