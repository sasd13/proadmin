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

import com.sasd13.proadmin.backend.bean.Teacher;
import com.sasd13.proadmin.backend.service.ITeacherService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.TeacherAdapterB2I;
import com.sasd13.proadmin.backend.util.adapter.itf2bean.TeacherAdapterI2B;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

@RestController
@RequestMapping("/teachers")
public class TeacherController extends Controller {

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
	public ResponseEntity<ResponseBean> search(@RequestParam(value = "intermediary", required = true) String intermediary) {
		LOGGER.info("[Proadmin-Backend] Teacher : read");

		try {
			Teacher result = teacherService.read(intermediary);
			ResponseBean responseBean = new ResponseBean();
			responseBean.getContext().setPaginationCurrentItems(String.valueOf(1));
			responseBean.setData(new TeacherAdapterB2I().adapt(result));

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
			List<Teacher> results = teacherService.read(searchBean.getCriterias());
			ResponseBean responseBean = new ResponseBean();
			List<TeacherBean> list = new ArrayList<>();
			TeacherAdapterB2I adapter = new TeacherAdapterB2I();

			for (Teacher result : results) {
				list.add(adapter.adapt(result));
			}

			addHeaders(searchBean, responseBean);
			responseBean.getContext().setPaginationCurrentItems(String.valueOf(list.size()));
			responseBean.setData(list);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
