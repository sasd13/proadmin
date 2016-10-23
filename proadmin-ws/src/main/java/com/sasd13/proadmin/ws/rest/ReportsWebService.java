/*
 * To change this license header, choose License Headers in Report Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.running.Report;

/**
 *
 * @author Samir
 */
@WebServlet("/reports")
public class ReportsWebService extends AbstractWebService<Report> {

	private static final long serialVersionUID = 1739619237509751567L;

	@Override
	protected Class<Report> getEntityClass() {
		return Report.class;
	}
}
