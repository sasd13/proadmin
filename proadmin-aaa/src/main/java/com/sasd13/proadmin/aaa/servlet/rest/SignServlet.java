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

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.proadmin.aaa.dao.CredentialUpdateWrapper;
import com.sasd13.proadmin.aaa.service.CredentialManageService;
import com.sasd13.proadmin.aaa.validator.CredentialValidator;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 *
 * @author Samir
 */
@WebServlet("/sign")
public class SignServlet extends AAAServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final Logger LOG = Logger.getLogger(SignServlet.class);

	private IValidator<Credential> validator;
	private IManageService<Credential> manageService;

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	@Override
	public void init() throws ServletException {
		super.init();

		validator = new CredentialValidator();
		manageService = new CredentialManageService();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doPost");

		try {
			Credential credential = readFromRequest(req);

			validator.validate(credential);
			manageService.create((List<Credential>) Arrays.asList(new Credential[] { credential }));
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doPut");

		try {
			Credential credential = readFromRequest(req);

			validator.validate(credential);

			CredentialUpdateWrapper credentialUpdateWrapper = new CredentialUpdateWrapper();
			credentialUpdateWrapper.setWrapped(credential);
			credentialUpdateWrapper.setUsername(credential.getUsername());

			manageService.update((List<IUpdateWrapper<Credential>>) Arrays.asList(new CredentialUpdateWrapper[] { credentialUpdateWrapper }));
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doDelete");

		try {
			Credential credential = readFromRequest(req);

			validator.validate(credential);
			manageService.delete((List<Credential>) Arrays.asList(new Credential[] { credential }));
		} catch (Exception e) {
			handleError(e, resp);
		}
	}
}
