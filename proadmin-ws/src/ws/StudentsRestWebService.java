/*
 * To change this license header, choose License Headers in Student Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.DAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ws.db.DAOFactory;

/**
 *
 * @author Samir
 */
@WebServlet("/students")
public class StudentsRestWebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        Gson gson = new GsonBuilder().create();
        String jsonData;
        
        DAO dao = DAOFactory.make();
        
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
        
        resp.getWriter().write(jsonData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStudent = req.getParameter("student");
        String jsonTeam = req.getParameter("team");
        
        Gson gson = new GsonBuilder().create();
        Student student = gson.fromJson(jsonStudent, Student.class);
        Team team = gson.fromJson(jsonTeam, Team.class);
        
        DAO dao = DAOFactory.make();
        
        dao.open();        
        long id = dao.insertStudent(student, team.getId());
        dao.close();
        
        resp.getWriter().write(gson.toJson(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStudent = req.getParameter("student");
        
        Gson gson = new GsonBuilder().create();
        Student student = gson.fromJson(jsonStudent, Student.class);
        
        DAO dao = DAOFactory.make();
        
        dao.open();
        dao.updateStudent(student);
        dao.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        DAO dao = DAOFactory.make();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            dao.deleteStudent(id);
        } else {
            List<Student> students = dao.selectAllStudents();
            for (Student student : students) {
                dao.deleteStudent(student.getId());
            }
        }
        
        dao.close();
    }
}
