/*
 * To change this license header, choose License Headers in Student Properties.
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
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IStudentTeamService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

/**
 *
 * @author Samir
 */
@WebServlet("/studentTeams")
public class StudentTeamController extends Controller {

	private static final long serialVersionUID = 9150723342716530893L;

	private static final Logger LOGGER = Logger.getLogger(StudentTeamController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] StudentTeam : GET");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IStudentTeamService studentTeamService = (IStudentTeamService) ServiceFactory.make(IStudentTeamService.class, dao);
			List<StudentTeam> results = null;

			if (parameters.isEmpty()) {
				results = studentTeamService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = studentTeamService.read(parameters);
			}

			writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] StudentTeam : POST");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<StudentTeam> studentTeams = (List<StudentTeam>) readFromRequest(req, StudentTeam.class, null);
			IStudentTeamService studentTeamService = (IStudentTeamService) ServiceFactory.make(IStudentTeamService.class, dao);

			for (StudentTeam studentTeam : studentTeams) {
				studentTeamService.create(studentTeam);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] StudentTeam : DELETE");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<StudentTeam> studentTeams = (List<StudentTeam>) readFromRequest(req, StudentTeam.class, null);
			IStudentTeamService studentTeamService = (IStudentTeamService) ServiceFactory.make(IStudentTeamService.class, dao);

			for (StudentTeam studentTeam : studentTeams) {
				studentTeamService.delete(studentTeam);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
