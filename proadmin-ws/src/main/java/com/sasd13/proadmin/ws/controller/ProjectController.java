/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.itf.RequestBean;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;
import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IProjectService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.ProjectAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.ProjectAdapterI2B;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.update.ProjectUpdateAdapterI2B;

/**
 *
 * @author Samir
 */
@WebServlet("/projects")
public class ProjectController extends Controller {

	private static final long serialVersionUID = 1622591818424740680L;

	private static final Logger LOGGER = Logger.getLogger(ProjectController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Project : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			IProjectService projectService = (IProjectService) ServiceFactory.make(IProjectService.class, dao);
			List<Project> results = projectService.read(parameters);
			ResponseBean responseBean = new ResponseBean();
			List<ProjectBean> list = new ArrayList<>();
			ProjectAdapterB2I adapter = new ProjectAdapterB2I();

			for (Project result : results) {
				list.add(adapter.adapt(result));
			}

			addHeaders(responseBean);
			responseBean.getContext().setPaginationTotalItems(String.valueOf(list.size()));
			responseBean.setData(list);

			writeToResponse(resp, ParserFactory.make(Constants.RESPONSE_CONTENT_TYPE).toString(responseBean));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Project : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RequestBean requestBean = readFromRequest(req, RequestBean.class);
			IProjectService projectService = (IProjectService) ServiceFactory.make(IProjectService.class, dao);
			ProjectBean projectBean = (ProjectBean) requestBean.getData();
			ProjectAdapterI2B adapter = new ProjectAdapterI2B();

			projectService.create(adapter.adapt(projectBean));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Project : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RequestBean requestBean = readFromRequest(req, RequestBean.class);
			IProjectService projectService = (IProjectService) ServiceFactory.make(IProjectService.class, dao);
			ProjectBean projectBean = (ProjectBean) requestBean.getData();
			ProjectUpdateAdapterI2B adapter = new ProjectUpdateAdapterI2B();

			projectService.update(adapter.adapt(projectBean));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Project : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RequestBean requestBean = readFromRequest(req, RequestBean.class);
			IProjectService projectService = (IProjectService) ServiceFactory.make(IProjectService.class, dao);
			ProjectBean projectBean = (ProjectBean) requestBean.getData();
			ProjectAdapterI2B adapter = new ProjectAdapterI2B();

			projectService.delete(adapter.adapt(projectBean));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
