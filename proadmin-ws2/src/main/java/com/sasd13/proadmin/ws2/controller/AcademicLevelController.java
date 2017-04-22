package com.sasd13.proadmin.ws2.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.bean.level.AcademicLevel;
import com.sasd13.proadmin.ws2.service.IAcademicLevelService;

@RestController
@RequestMapping("/academicLevel")
public class AcademicLevelController {

	private static final Logger LOGGER = Logger.getLogger(AcademicLevelController.class);

	@Autowired
	private IAcademicLevelService academicLevelService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AcademicLevel>> get() {
		LOGGER.info("Get");

		try {
			List<AcademicLevel> academicLevels = academicLevelService.readAll();

			return new ResponseEntity<List<AcademicLevel>>(academicLevels, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<AcademicLevel>>(HttpStatus.EXPECTATION_FAILED);
	}
}
