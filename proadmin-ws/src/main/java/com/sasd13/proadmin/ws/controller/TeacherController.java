/*
 * To change this license header, choose License Headers in Teacher Properties.
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
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherRequestBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherResponseBean;
import com.sasd13.proadmin.ws.bean.Teacher;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.ITeacherService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.TeacherAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.TeacherAdapterI2B;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.update.TeacherUpdateAdapterI2B;

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
		LOGGER.info("[Proadmin-WS] Teacher : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			ITeacherService teacherService = (ITeacherService) ServiceFactory.make(ITeacherService.class, dao);
			List<Teacher> results = teacherService.read(parameters);
			TeacherResponseBean responseBean = new TeacherResponseBean();
			List<TeacherBean> list = new ArrayList<>();
			TeacherAdapterB2I adapter = new TeacherAdapterB2I();

			for (Teacher result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(responseBean, list.size());

			writeToResponse(resp, ParserFactory.make(Constants.RESPONSE_CONTENT_TYPE).toString(responseBean));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Teacher : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			TeacherRequestBean requestBean = readFromRequest(req, TeacherRequestBean.class);
			ITeacherService teacherService = (ITeacherService) ServiceFactory.make(ITeacherService.class, dao);

			teacherService.create(new TeacherAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Teacher : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			TeacherRequestBean requestBean = readFromRequest(req, TeacherRequestBean.class);
			ITeacherService teacherService = (ITeacherService) ServiceFactory.make(ITeacherService.class, dao);

			teacherService.update(new TeacherUpdateAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Teacher : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			TeacherRequestBean requestBean = readFromRequest(req, TeacherRequestBean.class);
			ITeacherService teacherService = (ITeacherService) ServiceFactory.make(ITeacherService.class, dao);

			teacherService.delete(new TeacherAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
