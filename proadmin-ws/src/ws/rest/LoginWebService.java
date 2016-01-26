/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import java.io.IOException;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;
import db.JDBCTeacherDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LoginWebService extends HttpServlet {
	
	private DAO dao = JDBCDAO.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = 0;
		
		try {
			Teacher teacherFromRequest = (Teacher) ParserService.readAndParseDataFromRequest(req, Teacher.class);
			
			try {
				dao.open();
				
				Teacher teacher = ((JDBCTeacherDAO) dao.getEntityDAO(Teacher.class)).selectByNumber(teacherFromRequest.getNumber());
				
				id = (teacher == null)
						? 0
						: (!teacherFromRequest.getPassword().equals(teacher.getPassword()))
							? -1
							: teacher.getId();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dao.close();
			}
		} catch (ClassCastException | NullPointerException e) {
			e.printStackTrace();
		}
		
		ParserService.parseAndWriteDataToResponse(req, resp, id);
	}
}
