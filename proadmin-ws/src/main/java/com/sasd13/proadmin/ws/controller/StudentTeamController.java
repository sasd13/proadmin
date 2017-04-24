/*
 * To change this license header, choose License Headers in Student Properties.
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
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;
import com.sasd13.proadmin.ws.bean.StudentTeam;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IStudentTeamService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.StudentTeamAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.StudentTeamAdapterI2B;

/**
 *
 * @author Samir
 */
@WebServlet("/studentTeams")
public class StudentTeamController extends Controller {

	private static final long serialVersionUID = -8420663078069520851L;

	private static final Logger LOGGER = Logger.getLogger(StudentTeamController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] StudentTeam : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			IStudentTeamService studentTeamService = (IStudentTeamService) ServiceFactory.make(IStudentTeamService.class, dao);
			List<StudentTeam> results = studentTeamService.read(parameters);
			ResponseBean responseBean = new ResponseBean();
			List<StudentTeamBean> list = new ArrayList<>();
			StudentTeamAdapterB2I adapter = new StudentTeamAdapterB2I();

			for (StudentTeam result : results) {
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

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] StudentTeam : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RequestBean requestBean = readFromRequest(req, RequestBean.class);
			IStudentTeamService studentTeamService = (IStudentTeamService) ServiceFactory.make(IStudentTeamService.class, dao);
			List<StudentTeamBean> studentTeamBeans = (List<StudentTeamBean>) requestBean.getData();
			List<StudentTeam> studentTeams = new ArrayList<>();
			StudentTeamAdapterI2B adapter = new StudentTeamAdapterI2B();

			for (StudentTeamBean studentTeamBean : studentTeamBeans) {
				studentTeams.add(adapter.adapt(studentTeamBean));
			}

			studentTeamService.create(studentTeams);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] StudentTeam : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RequestBean requestBean = readFromRequest(req, RequestBean.class);
			IStudentTeamService studentTeamService = (IStudentTeamService) ServiceFactory.make(IStudentTeamService.class, dao);
			List<StudentTeamBean> studentTeamBeans = (List<StudentTeamBean>) requestBean.getData();
			List<StudentTeam> studentTeams = new ArrayList<>();
			StudentTeamAdapterI2B adapter = new StudentTeamAdapterI2B();

			for (StudentTeamBean studentTeamBean : studentTeamBeans) {
				studentTeams.add(adapter.adapt(studentTeamBean));
			}

			studentTeamService.create(studentTeams);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
