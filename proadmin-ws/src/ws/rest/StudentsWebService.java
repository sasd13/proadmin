/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import com.sasd13.proadmin.core.bean.member.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Samir
 */
@WebServlet("/students")
public class StudentsWebService extends AbstractWebService<Student> {
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		requestProcessor = new RequestProcessor<>(Student.class);
	}
}
