/*
 * To change this license header, choose License Headers in Running Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.running.Running;

/**
 *
 * @author Samir
 */
@WebServlet("/runnings")
public class RunningsWebService extends AbstractWebService<Running> {

	@Override
	protected Class<Running> getEntityClass() {
		return Running.class;
	}
}
