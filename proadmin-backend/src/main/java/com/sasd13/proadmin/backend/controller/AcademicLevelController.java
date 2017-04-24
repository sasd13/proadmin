package com.sasd13.proadmin.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.bean.AcademicLevel;
import com.sasd13.proadmin.backend.service.IAcademicLevelService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.AcademicLevelAdapterB2I;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;

@RestController
@RequestMapping("/academicLevels")
public class AcademicLevelController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(AcademicLevelController.class);

	@Autowired
	private IAcademicLevelService academicLevelService;

	@RequestMapping(path = "/read", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> read() {
		LOGGER.info("[Proadmin-Backend] AcademicLevel : read");

		try {
			List<AcademicLevel> results = academicLevelService.readAll();
			ResponseBean responseBean = new ResponseBean();
			List<AcademicLevelBean> list = new ArrayList<>();
			AcademicLevelAdapterB2I adapter = new AcademicLevelAdapterB2I();

			for (AcademicLevel result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.getContext().setPaginationTotalItems(String.valueOf(list.size()));
			responseBean.setData(list);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
