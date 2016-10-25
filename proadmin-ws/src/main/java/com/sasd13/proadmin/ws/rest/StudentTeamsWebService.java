/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.http.URLQueryEncoder;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.Names;
import com.sasd13.proadmin.util.ws.EnumWSError;
import com.sasd13.proadmin.ws.Config;
import com.sasd13.proadmin.ws.WSException;
import com.sasd13.proadmin.ws.service.IManageService;
import com.sasd13.proadmin.ws.service.IReadService;
import com.sasd13.proadmin.ws.service.ManageServiceFactory;
import com.sasd13.proadmin.ws.service.ReadServiceFactory;

/**
 *
 * @author Samir
 */
@WebServlet("/studentteams")
public class StudentTeamsWebService extends HttpServlet {

	private static final long serialVersionUID = -1702761496823324620L;

	private static final Logger LOG = Logger.getLogger(StudentTeam.class);
	private static final String RESPONSE_CONTENT_TYPE = Config.getInfo(Names.WS_RESPONSE_CONTENT_TYPE);

	IReadService<StudentTeam> readService;
	IManageService<StudentTeam> manageService;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			readService = ReadServiceFactory.make(StudentTeam.class);
			manageService = ManageServiceFactory.make(StudentTeam.class);
		} catch (WSException e) {
			LOG.error(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);

		List<StudentTeam> studentTeamsToResponse = new ArrayList<>();
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			URLQueryEncoder.decode(parameters);

			if (!parameters.isEmpty()) {
				studentTeamsToResponse.addAll(readService.read(parameters));
			} else {
				studentTeamsToResponse.addAll(readService.readAll());
			}

			resp.setContentType(RESPONSE_CONTENT_TYPE);
			Stream.writeAndClose(resp.getWriter(), ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(studentTeamsToResponse));
		} catch (ParserException | WSException e) {
			catchError(e, resp);
		}
	}

	private void catchError(Exception e, HttpServletResponse resp) throws IOException {
		LOG.error(e);

		EnumWSError wsError = WSErrorFactory.make(e);

		resp.setHeader(EnumHttpHeader.WS_ERROR.getName(), String.valueOf(wsError.getCode()));
		Stream.writeAndClose(resp.getWriter(), wsError.getLabel());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			StudentTeam studentTeam = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), StudentTeam.class);

			manageService.create(studentTeam);
		} catch (ParserException | WSException e) {
			catchError(e, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			StudentTeam studentTeam = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), StudentTeam.class);

			manageService.update(studentTeam);
		} catch (ParserException | WSException e) {
			catchError(e, resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			StudentTeam studentTeam = ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), StudentTeam.class);

			manageService.delete(studentTeam);
		} catch (ParserException | WSException e) {
			catchError(e, resp);
		}
	}
}
