package com.sasd13.proadmin.ws2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;
import com.sasd13.proadmin.ws2.service.IProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	private static final Logger LOGGER = Logger.getLogger(ProjectController.class);

	@Autowired
	private IProjectService projectService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Project>> get(@RequestParam(value = "codes", required = false) List<String> codes) {
		LOGGER.info("Get");

		try {
			List<Project> projects = projectService.read(getParameters(codes));

			return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<List<Project>>(HttpStatus.EXPECTATION_FAILED);
	}

	private Map<String, String[]> getParameters(List<String> codes) {
		Map<String, String[]> parameters = new HashMap<>();

		if (codes != null) {
			for (String code : codes) {
				parameters.put(EnumParameter.CODE.getName(), new String[] { code });
			}
		}

		return parameters;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> post(@RequestBody Project project) {
		LOGGER.info("Post");

		try {
			projectService.create(project);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Integer> put(@RequestBody List<ProjectUpdateWrapper> updateWrappers) {
		LOGGER.info("Put");

		try {
			projectService.update(updateWrappers);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@RequestBody List<Project> projects) {
		LOGGER.info("Delete");

		try {
			projectService.delete(projects);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}
}
