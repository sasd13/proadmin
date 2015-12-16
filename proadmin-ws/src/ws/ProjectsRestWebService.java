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

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ws.db.JDBCDAO;

/**
 *
 * @author Samir
 */
@WebServlet("/projects")
public class ProjectsRestWebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        Gson gson = new GsonBuilder().create();
        String jsonData;
        
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
        
        resp.getWriter().write(jsonData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonProject = req.getParameter("project");
        
        Gson gson = new GsonBuilder().create();
        Project project = gson.fromJson(jsonProject, Project.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();        
        long id = dao.insertProject(project);
        dao.close();
        
        resp.getWriter().write(gson.toJson(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonProject = req.getParameter("project");
        
        Gson gson = new GsonBuilder().create();
        Project project = gson.fromJson(jsonProject, Project.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        dao.updateProject(project);
        dao.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
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
