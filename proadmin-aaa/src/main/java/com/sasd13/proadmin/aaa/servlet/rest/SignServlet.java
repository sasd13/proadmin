/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.parser.IParser;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.aaa.AAAConstants;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;
import com.sasd13.proadmin.aaa.service.CredentialService;
import com.sasd13.proadmin.aaa.util.validator.CredentialUpdateWrapperValidator;
import com.sasd13.proadmin.aaa.util.validator.CredentialValidator;
import com.sasd13.proadmin.util.wrapper.WrapperException;
import com.sasd13.proadmin.util.wrapper.update.credential.CredentialUpdateWrapper;

/**
 *
 * @author Samir
 */
@WebServlet("/sign")
public class SignServlet extends AAAServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final Logger LOGGER = Logger.getLogger(SignServlet.class);

	private IValidator<Credential> validator;
	private IValidator<IUpdateWrapper<Credential>> updateWrapperValidator;

	@Override
	public void init() throws ServletException {
		super.init();

		validator = new CredentialValidator();
		updateWrapperValidator = new CredentialUpdateWrapperValidator();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPost");

		ICredentialDAO dao = (ICredentialDAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			Credential credential = readFromRequest(req);
			IManageService<Credential> manageService = new CredentialService(dao);

			validator.validate(credential);
			manageService.create(credential);
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPut");

		ICredentialDAO dao = (ICredentialDAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			IUpdateWrapper<Credential> updateWrapper = readUpdateWrappersFromRequest(req).get(0);
			IManageService<Credential> manageService = new CredentialService(dao);

			updateWrapperValidator.validate(updateWrapper);
			manageService.update(updateWrapper);
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@SuppressWarnings("unchecked")
	private List<IUpdateWrapper<Credential>> readUpdateWrappersFromRequest(HttpServletRequest req) throws IOException, ParserException, WrapperException {
		IParser parser = ParserFactory.make(req.getContentType());
		String message = Stream.read(req.getReader());
		Class<?> mClass = CredentialUpdateWrapper.class;

		return (List<IUpdateWrapper<Credential>>) parser.fromStringArray(message, mClass);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doDelete");

		ICredentialDAO dao = (ICredentialDAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			Credential credential = readFromRequest(req);
			IManageService<Credential> manageService = new CredentialService(dao);

			validator.validate(credential);
			manageService.delete(credential);
		} catch (Exception e) {
			handleError(e, resp);
		}
	}
}
