/*
 * To change this license header, choose License Headers in Report Properties.
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
import com.sasd13.proadmin.itf.bean.report.ReportBean;
import com.sasd13.proadmin.itf.bean.report.ReportRequestBean;
import com.sasd13.proadmin.itf.bean.report.ReportResponseBean;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IReportService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.ReportAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.ReportAdapterI2B;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.update.ReportUpdateAdapterI2B;

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
		LOGGER.info("[Proadmin-WS] Report : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			IReportService reportService = (IReportService) ServiceFactory.make(IReportService.class, dao);
			List<Report> results = reportService.read(parameters);
			ReportResponseBean responseBean = new ReportResponseBean();
			List<ReportBean> list = new ArrayList<>();
			ReportAdapterB2I adapter = new ReportAdapterB2I();

			for (Report result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(responseBean, list.size());

			writeToResponse(resp, responseBean);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Report : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			ReportRequestBean requestBean = readFromRequest(req, ReportRequestBean.class);
			IReportService reportService = (IReportService) ServiceFactory.make(IReportService.class, dao);

			reportService.create(new ReportAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Report : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			ReportRequestBean requestBean = readFromRequest(req, ReportRequestBean.class);
			IReportService reportService = (IReportService) ServiceFactory.make(IReportService.class, dao);

			reportService.update(new ReportUpdateAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Report : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			ReportRequestBean requestBean = readFromRequest(req, ReportRequestBean.class);
			IReportService reportService = (IReportService) ServiceFactory.make(IReportService.class, dao);

			reportService.delete(new ReportAdapterI2B().adapt(requestBean.getData().get(0)));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
