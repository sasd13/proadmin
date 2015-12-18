/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.proadmin.core.bean.project.Project;
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
@WebServlet("/projects")
public class ProjectsRestWebService extends HttpServlet {

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
            Project project = dao.selectProject(id);
            
            jsonData = gson.toJson(project);
        } else {
            List<Project> projects = dao.selectAllProjects();
            
            jsonData = gson.toJson(projects);
        }
        
        dao.close();
        
		return jsonData;
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	Gson gson = new GsonBuilder().create();
    	
    	String jsonData = getData(req);
        long id = create(gson, jsonData);
        
        resp.getWriter().write(gson.toJson(id));
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
    
    private long create(Gson gson, String jsonData) {
    	long id = 0;
        
        if (jsonData.startsWith("{") && jsonData.endsWith("}")) {
        	Project project = gson.fromJson(jsonData, Project.class);
        	
        	DAO dao = JDBCDAO.getInstance();
            
            dao.open();
        	id = dao.insertProject(project);
        	dao.close();
        }
        
        return id;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonData = getData(req);
        
        update(jsonData);
    }

	private void update(String jsonData) {
		Gson gson = new GsonBuilder().create();
    	DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (jsonData.startsWith("{") && jsonData.endsWith("}")) {
        	Project project = gson.fromJson(jsonData, Project.class);
        	
        	dao.updateProject(project);
        } else if (jsonData.startsWith("[") && jsonData.endsWith("]")) {
        	Project[] projects = gson.fromJson(jsonData, Project[].class);
        	
        	for (Project project : projects) {
        		dao.updateProject(project);
        	}
        }
        
    	dao.close();
	}

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {    	
        String paramsId = req.getParameter("id");
        
        delete(paramsId);
    }

	private void delete(String paramsId) {
		DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            dao.deleteProject(id);
        } else {
            List<Project> projects = dao.selectAllProjects();
            for (Project project : projects) {
                dao.deleteProject(project.getId());
            }
        }
        
        dao.close();
	}
}
