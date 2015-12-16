/*
 * To change this license header, choose License Headers in Teacher Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

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
@WebServlet("/teachers")
public class TeachersRestWebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        Gson gson = new GsonBuilder().create();
        String jsonData;
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            Teacher teacher = dao.selectTeacher(id);
            
            jsonData = gson.toJson(teacher);
        } else {
            List<Teacher> teachers = dao.selectAllTeachers();
            
            jsonData = gson.toJson(teachers);
        }
        
        dao.close();
        
        resp.getWriter().write(jsonData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonTeacher = req.getParameter("teacher");
        
        Gson gson = new GsonBuilder().create();
        Teacher teacher = gson.fromJson(jsonTeacher, Teacher.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();        
        long id = dao.insertTeacher(teacher);
        dao.close();
        
        resp.getWriter().write(gson.toJson(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonTeacher = req.getParameter("teacher");
        
        Gson gson = new GsonBuilder().create();
        Teacher teacher = gson.fromJson(jsonTeacher, Teacher.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        dao.updateTeacher(teacher);
        dao.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            dao.deleteTeacher(id);
        } else {
            List<Teacher> teachers = dao.selectAllTeachers();
            for (Teacher teacher : teachers) {
                dao.deleteTeacher(teacher.getId());
            }
        }
        
        dao.close();
    }
}
