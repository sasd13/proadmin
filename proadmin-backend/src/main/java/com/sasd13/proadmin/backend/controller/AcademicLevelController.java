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

import com.sasd13.proadmin.backend.model.AcademicLevel;
import com.sasd13.proadmin.backend.service.IAcademicLevelService;
import com.sasd13.proadmin.backend.util.adapter.model2itf.AcademicLevelAdapterM2I;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelResponseBean;

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
			AcademicLevelResponseBean responseBean = new AcademicLevelResponseBean();
			List<AcademicLevelBean> list = new ArrayList<>();
			AcademicLevelAdapterM2I adapter = new AcademicLevelAdapterM2I();

			for (AcademicLevel result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			responseBean.getHeader().getApplicativeContext().setPaginationTotalItems(String.valueOf(list.size()));

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
