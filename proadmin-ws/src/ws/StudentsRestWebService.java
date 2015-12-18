/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samir
 */
@WebServlet("/students")
public class StudentsRestWebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        String jsonData = read(paramsId);
        
        resp.getWriter().write(jsonData);
    }

	private String read(String paramsId) {
		String jsonData;
		
		Gson gson = new GsonBuilder().create();
		DAO dao = JDBCDAO.getInstance();
		
		dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            Student student = dao.selectStudent(id);
            
            jsonData = gson.toJson(student);
        } else {
            List<Student> students = dao.selectAllStudents();
            
            jsonData = gson.toJson(students);
        }
        
        dao.close();
        
		return jsonData;
	}

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonData = getData(req);
        
        update(jsonData);
    }
    
    private String getData(HttpServletRequest req) {
    	StringBuilder sBuilder = new StringBuilder();
    	
    	try {
    		BufferedReader reader = req.getReader();
            String line;

            while ((line = reader.readLine()) != null) {
                sBuilder.append(line);
            }
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        
        return sBuilder.toString().trim();
    }

	private void update(String jsonData) {
		Gson gson = new GsonBuilder().create();
    	DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (jsonData.startsWith("{") && jsonData.endsWith("}")) {
        	Student student = gson.fromJson(jsonData, Student.class);
        	
        	dao.updateStudent(student);
        } else if (jsonData.startsWith("[") && jsonData.endsWith("]")) {
        	Student[] students = gson.fromJson(jsonData, Student[].class);
        	
        	for (Student student : students) {
        		dao.updateStudent(student);
        	}
        }
        
    	dao.close();
	}
}
