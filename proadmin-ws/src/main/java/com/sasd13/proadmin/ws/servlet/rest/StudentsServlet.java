/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.servlet.rest;

import javax.servlet.annotation.WebServlet;

import com.sasd13.proadmin.bean.member.Student;

/**
 *
 * @author Samir
 */
@WebServlet("/students")
public class StudentsServlet extends BeansServlet<Student> {

	private static final long serialVersionUID = -3033177600656496944L;

	@Override
	protected Class<Student> getBeanClass() {
		return Student.class;
	}
}
