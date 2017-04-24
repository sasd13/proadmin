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
import com.sasd13.proadmin.itf.bean.student.StudentBean;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IStudentService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.StudentAdapterB2I;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.StudentAdapterI2B;
import com.sasd13.proadmin.ws.util.adapter.itf2bean.update.StudentUpdateAdapterI2B;

/**
 *
 * @author Samir
 */
@WebServlet("/students")
public class StudentController extends Controller {

	private static final long serialVersionUID = -3033177600656496944L;

	private static final Logger LOGGER = Logger.getLogger(StudentController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Student : search");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryUtils.decode(parameters);

			IStudentService studentService = (IStudentService) ServiceFactory.make(IStudentService.class, dao);
			List<Student> results = studentService.read(parameters);
			ResponseBean responseBean = new ResponseBean();
			List<StudentBean> list = new ArrayList<>();
			StudentAdapterB2I adapter = new StudentAdapterB2I();

			for (Student result : results) {
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
		LOGGER.info("[Proadmin-WS] Student : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RequestBean requestBean = readFromRequest(req, RequestBean.class);
			IStudentService studentService = (IStudentService) ServiceFactory.make(IStudentService.class, dao);
			StudentBean studentBean = (StudentBean) requestBean.getData();
			StudentAdapterI2B adapter = new StudentAdapterI2B();

			studentService.create(adapter.adapt(studentBean));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Student : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RequestBean requestBean = readFromRequest(req, RequestBean.class);
			IStudentService studentService = (IStudentService) ServiceFactory.make(IStudentService.class, dao);
			StudentBean studentBean = (StudentBean) requestBean.getData();
			StudentUpdateAdapterI2B adapter = new StudentUpdateAdapterI2B();

			studentService.update(adapter.adapt(studentBean));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] Student : delete");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			RequestBean requestBean = readFromRequest(req, RequestBean.class);
			IStudentService studentService = (IStudentService) ServiceFactory.make(IStudentService.class, dao);
			StudentBean studentBean = (StudentBean) requestBean.getData();
			StudentAdapterI2B adapter = new StudentAdapterI2B();

			studentService.delete(adapter.adapt(studentBean));
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
