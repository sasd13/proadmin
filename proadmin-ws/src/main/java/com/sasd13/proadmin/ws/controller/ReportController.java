/*
 * To change this license header, choose License Headers in Report Properties.
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
import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IReportService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;

/**
 *
 * @author Samir
 */
@WebServlet("/reports")
public class ReportController extends Controller {

	private static final long serialVersionUID = -574346007593042394L;

	private static final Logger LOGGER = Logger.getLogger(ReportController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Report : GET");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IReportService reportService = (IReportService) ServiceFactory.make(IReportService.class, dao);
			List<IReport> results = null;

			if (parameters.isEmpty()) {
				results = reportService.readAll();
			} else {
				URLQueryUtils.decode(parameters);

				results = reportService.read(parameters);
			}

			writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(results));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Report : POST");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<IReport> iReports = (List<IReport>) readFromRequest(req, IReport.class, null);
			IReportService reportService = (IReportService) ServiceFactory.make(IReportService.class, dao);

			for (IReport iReport : iReports) {
				reportService.create(iReport);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Report : PUT");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<ReportUpdate> updateWrappers = (List<ReportUpdate>) readFromRequest(req, ReportUpdate.class, null);
			IReportService reportService = (IReportService) ServiceFactory.make(IReportService.class, dao);

			for (ReportUpdate updateWrapper : updateWrappers) {
				reportService.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Report : DELETE");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<IReport> iReports = (List<IReport>) readFromRequest(req, IReport.class, null);
			IReportService reportService = (IReportService) ServiceFactory.make(IReportService.class, dao);

			for (IReport iReport : iReports) {
				reportService.delete(iReport);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
