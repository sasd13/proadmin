/*
 * To change this license header, choose License Headers in Team Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import com.sasd13.proadmin.core.bean.running.Team;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Samir
 */
@WebServlet("/teams")
public class TeamsWebService extends AbstractWebService<Team> {
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		requestProcessor = new RequestProcessor<>(Team.class);
	}
}
