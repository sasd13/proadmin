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
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.student.StudentBean;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.bean.update.StudentUpdate;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.IStudentService;
import com.sasd13.proadmin.ws.service.ServiceFactory;
import com.sasd13.proadmin.ws.util.Constants;
import com.sasd13.proadmin.ws.util.adapter.bean2itf.StudentAdapterB2I;

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
		LOGGER.info("[Proadmin-WS] Student : read");

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

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] student : POST");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<Student> students = (List<Student>) readFromRequest(req, Student.class, null);
			IStudentService studentService = (IStudentService) ServiceFactory.make(IStudentService.class, dao);

			for (Student student : students) {
				studentService.create(student);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] student : PUT");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<StudentUpdate> updateWrappers = (List<StudentUpdate>) readFromRequest(req, StudentUpdate.class, null);
			IStudentService studentService = (IStudentService) ServiceFactory.make(IStudentService.class, dao);

			for (StudentUpdate updateWrapper : updateWrappers) {
				studentService.update(updateWrapper);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-WS] student : DELETE");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			List<Student> students = (List<Student>) readFromRequest(req, Student.class, null);
			IStudentService studentService = (IStudentService) ServiceFactory.make(IStudentService.class, dao);

			for (Student student : students) {
				studentService.delete(student);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
