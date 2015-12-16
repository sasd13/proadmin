/*
 * To change this license header, choose License Headers in Running Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.sasd13.proadmin.core.bean.running.Running;
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
@WebServlet("/runnings")
public class RunningsRestWebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        Gson gson = new GsonBuilder().create();
        String jsonData;
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            Running running = dao.selectRunning(id);
            
            jsonData = gson.toJson(running);
        } else {
            List<Running> runnings = dao.selectAllRunnings();
            
            jsonData = gson.toJson(runnings);
        }
        
        dao.close();
        
        resp.getWriter().write(jsonData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonRunning = req.getParameter("running");
        
        Gson gson = new GsonBuilder().create();
        Running running = gson.fromJson(jsonRunning, Running.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();        
        long id = dao.insertRunning(running);
        dao.close();
        
        resp.getWriter().write(gson.toJson(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonRunning = req.getParameter("running");
        
        Gson gson = new GsonBuilder().create();
        Running running = gson.fromJson(jsonRunning, Running.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        dao.updateRunning(running);
        dao.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            dao.deleteRunning(id);
        } else {
            List<Running> runnings = dao.selectAllRunnings();
            for (Running running : runnings) {
                dao.deleteRunning(running.getId());
            }
        }
        
        dao.close();
    }
}
