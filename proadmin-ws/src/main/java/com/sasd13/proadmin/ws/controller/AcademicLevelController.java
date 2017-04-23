/*
 * To change this license header, choose License Headers in AcademicLevel Properties.
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
import com.sasd13.proadmin.bean.level.IAcademicLevel;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IAcademicLevelService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

/**
 *
 * @author Samir
 */
@WebServlet("/academicLevels")
public class AcademicLevelController extends Controller {

	private static final long serialVersionUID = -5517780261514018166L;

	private static final Logger LOGGER = Logger.getLogger(AcademicLevelController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] AcademicLevel : read");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IAcademicLevelService academicLevelService = (IAcademicLevelService) ServiceFactory.make(IAcademicLevelService.class, dao);
			List<IAcademicLevel> results = null;

			if (parameters.isEmpty()) {
				results = academicLevelService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = academicLevelService.read(parameters);
			}

			writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
