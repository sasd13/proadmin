/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.itf.bean.project.ProjectReadResponseBean;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IProjectService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

/**
 *
 * @author Samir
 */
@WebServlet("/projects")
public class ProjectsServlet extends BeansServlet {

	private static final long serialVersionUID = 1622591818424740680L;

	private static final Logger LOGGER = Logger.getLogger(ProjectsServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Project : GET");

		ProjectReadResponseBean responseBean = new ProjectReadResponseBean();
		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IProjectService projectService = (IProjectService) ServiceFactory.make(IProjectService.class, dao);
			List<Project> results = null;

			if (parameters.isEmpty()) {
				results = projectService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				String wantedItems;
				/*
				 * if ((wantedItems = req.getHeader(EnumHttpHeader.WANTED_ITEMS.getName())) != null) { responseBean.getContext().setPaginationWantedItems(wantedItems);
				 * 
				 * results = projectService.read(parameters, Integer.valueOf(wantedItems)); } else { results = projectService.read(parameters); }
				 */
			}

			responseBean.setData(results);
			responseBean.getContext().setAdditionalProperties(parameters);
			responseBean.getContext().setPaginationCurrentItems(String.valueOf(results.size()));

			writeToResponse(resp, LOGGER, responseBean);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Project : POST");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<Project> projects = (List<Project>) readFromRequest(req, Project.class, null);
			IProjectService projectService = (IProjectService) ServiceFactory.make(IProjectService.class, dao);

			for (Project project : projects) {
				projectService.create(project);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Project : PUT");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<ProjectUpdateWrapper> updateWrappers = (List<ProjectUpdateWrapper>) readFromRequest(req, ProjectUpdateWrapper.class, null);
			IProjectService projectService = (IProjectService) ServiceFactory.make(IProjectService.class, dao);

			for (ProjectUpdateWrapper updateWrapper : updateWrappers) {
				projectService.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Project : DELETE");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<Project> projects = (List<Project>) readFromRequest(req, Project.class, null);
			IProjectService projectService = (IProjectService) ServiceFactory.make(IProjectService.class, dao);

			for (Project project : projects) {
				projectService.delete(project);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
