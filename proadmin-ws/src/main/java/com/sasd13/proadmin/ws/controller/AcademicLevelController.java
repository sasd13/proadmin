/*
 * To change this license header, choose License Headers in AcademicLevel Properties.
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

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;
import com.sasd13.proadmin.ws.bean.AcademicLevel;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IAcademicLevelService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.AcademicLevelAdapterB2I;

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
		IAcademicLevelService academicLevelService = (IAcademicLevelService) ServiceFactory.make(IAcademicLevelService.class, dao);

		try {
			if (parameters.isEmpty()) {
				List<AcademicLevel> results = academicLevelService.readAll();
				ResponseBean responseBean = new ResponseBean();
				List<AcademicLevelBean> list = new ArrayList<>();
				AcademicLevelAdapterB2I adapter = new AcademicLevelAdapterB2I();

				for (AcademicLevel result : results) {
					list.add(adapter.adapt(result));
				}

				responseBean.getContext().setPaginationTotalItems(String.valueOf(list.size()));
				responseBean.setData(list);

				writeToResponse(resp, ParserFactory.make(Constants.RESPONSE_CONTENT_TYPE).toString(responseBean));
			} else {
				resp.setStatus(HttpStatus.SC_EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
