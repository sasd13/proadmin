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
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.model.Project;
import com.sasd13.proadmin.backend.service.IProjectService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.ProjectAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.ProjectAdapterM2I;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;
import com.sasd13.proadmin.itf.bean.project.ProjectRequestBean;
import com.sasd13.proadmin.itf.bean.project.ProjectResponseBean;

@RestController
@RequestMapping("/projects")
public class ProjectController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(ProjectController.class);

	@Autowired
	private IProjectService projectService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody ProjectRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Project : create");

		try {
			projectService.create(new ProjectAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody ProjectRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Project : update");

		try {
			projectService.update(new ProjectAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody ProjectRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Project : delete");

		try {
			projectService.delete(new ProjectAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] Project : search");

		try {
			List<Project> results = projectService.read(searchBean.getCriterias());
			ProjectResponseBean responseBean = new ProjectResponseBean();
			List<ProjectBean> list = new ArrayList<>();
			ProjectAdapterM2I adapter = new ProjectAdapterM2I();

			for (Project result : results) {
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
