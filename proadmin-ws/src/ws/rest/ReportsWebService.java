/*
 * To change this license header, choose License Headers in Report Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import com.sasd13.proadmin.core.bean.running.Report;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Samir
 */
@WebServlet("/reports")
public class ReportsWebService extends AbstractWebService<Report> {
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		requestProcessor = new RequestProcessor<>(Report.class);
	}
}
