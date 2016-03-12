/*
 * To change this license header, choose License Headers in Team Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.sasd13.proadmin.core.bean.member.Team;

import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Samir
 */
@WebServlet("/teams")
public class TeamsWebService extends AbstractWebService<Team> {
	
	@Override
	protected Class<Team> getEntityClass() {
		return Team.class;
	}
}
