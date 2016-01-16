/*
 * To change this license header, choose License Headers in Running Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import com.sasd13.proadmin.core.bean.running.Running;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Samir
 */
@WebServlet("/runnings")
public class RunningsWebService extends AbstractWebService<Running> {
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		requestProcessor = new RequestProcessor<>(Running.class);
	}
}
