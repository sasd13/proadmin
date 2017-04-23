/*
 * To change this license header, choose License Headers in Teacher Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.ITeacherService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

/**
 *
 * @author Samir
 */
@WebServlet("/teachers")
public class TeacherController extends Controller {

	private static final long serialVersionUID = 1390483642480552723L;

	private static final Logger LOGGER = Logger.getLogger(TeacherController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Teacher : GET");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			ITeacherService teacherService = (ITeacherService) ServiceFactory.make(ITeacherService.class, dao);
			List<ITeacher> results = null;

			if (parameters.isEmpty()) {
				results = teacherService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = teacherService.read(parameters);
			}

			writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Teacher : POST");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<ITeacher> iTeachers = (List<ITeacher>) readFromRequest(req, ITeacher.class, null);
			ITeacherService teacherService = (ITeacherService) ServiceFactory.make(ITeacherService.class, dao);

			for (ITeacher iTeacher : iTeachers) {
				teacherService.create(iTeacher);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Teacher : PUT");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<TeacherUpdateWrapper> updateWrappers = (List<TeacherUpdateWrapper>) readFromRequest(req, TeacherUpdateWrapper.class, null);
			ITeacherService teacherService = (ITeacherService) ServiceFactory.make(ITeacherService.class, dao);

			for (TeacherUpdateWrapper updateWrapper : updateWrappers) {
				teacherService.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Teacher : DELETE");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<ITeacher> iTeachers = (List<ITeacher>) readFromRequest(req, ITeacher.class, null);
			ITeacherService teacherService = (ITeacherService) ServiceFactory.make(ITeacherService.class, dao);

			for (ITeacher iTeacher : iTeachers) {
				teacherService.delete(iTeacher);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
