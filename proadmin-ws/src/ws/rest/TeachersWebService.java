/*
 * To change this license header, choose License Headers in Teacher Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.core.bean.member.Teacher;

/**
 *
 * @author Samir
 */
@WebServlet("/teachers")
public class TeachersWebService extends AbstractWebService<Teacher> {
	
	@Override
	protected Class<Teacher> getEntityClass() {
		return Teacher.class;
	}
}
