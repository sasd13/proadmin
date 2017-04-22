package com.sasd13.proadmin.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.bean.Project;
import com.sasd13.proadmin.backend.service.IProjectService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.ProjectAdapterB2I;
import com.sasd13.proadmin.backend.util.adapter.itf2bean.ProjectAdapterI2B;
import com.sasd13.proadmin.itf.RequestBean;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;

@RestController
@RequestMapping("/project")
public class ProjectController {

	private static final Logger LOGGER = Logger.getLogger(ProjectController.class);

	@Autowired
	private IProjectService projectService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody ProjectBean projectBean) {
		LOGGER.info("[Proadmin-Backend] Project : create");

		try {
			projectService.create(new ProjectAdapterI2B().adapt(projectBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody ProjectBean projectBean) {
		LOGGER.info("[Proadmin-Backend] Project : update");

		try {
			projectService.update(new ProjectAdapterI2B().adapt(projectBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody ProjectBean projectBean) {
		LOGGER.info("[Proadmin-Backend] Project : delete");

		try {
			projectService.delete(new ProjectAdapterI2B().adapt(projectBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean<ProjectBean>> search(@RequestBody RequestBean request) {
		LOGGER.info("[Proadmin-Backend] Project : search");

		try {
			List<Project> results = projectService.read(request.getCriteria());
			ResponseBean<ProjectBean> response = new ResponseBean<>();
			ProjectAdapterB2I adapter = new ProjectAdapterB2I();

			for (Project result : results) {
				response.getData().add(adapter.adapt(result));
			}

			return new ResponseEntity<ResponseBean<ProjectBean>>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean<ProjectBean>>(HttpStatus.EXPECTATION_FAILED);
	}
}
