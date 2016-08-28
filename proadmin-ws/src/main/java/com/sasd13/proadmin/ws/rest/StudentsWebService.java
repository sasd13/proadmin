/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.member.Student;

/**
 *
 * @author Samir
 */
@WebServlet("/students")
public class StudentsWebService extends AbstractWebService<Student> {

	@Override
	protected Class<Student> getEntityClass() {
		return Student.class;
	}
}
