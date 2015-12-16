/*
 * To change this license header, choose License Headers in Team Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.sasd13.proadmin.core.bean.running.Team;
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
@WebServlet("/teams")
public class TeamsRestWebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        Gson gson = new GsonBuilder().create();
        String jsonData;
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            Team team = dao.selectTeam(id);
            
            jsonData = gson.toJson(team);
        } else {
            List<Team> teams = dao.selectAllTeams();
            
            jsonData = gson.toJson(teams);
        }
        
        dao.close();
        
        resp.getWriter().write(jsonData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonTeam = req.getParameter("team");
        
        Gson gson = new GsonBuilder().create();
        Team team = gson.fromJson(jsonTeam, Team.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();        
        long id = dao.insertTeam(team);
        dao.close();
        
        resp.getWriter().write(gson.toJson(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonTeam = req.getParameter("team");
        
        Gson gson = new GsonBuilder().create();
        Team team = gson.fromJson(jsonTeam, Team.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        dao.updateTeam(team);
        dao.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            dao.deleteTeam(id);
        } else {
            List<Team> teams = dao.selectAllTeams();
            for (Team team : teams) {
                dao.deleteTeam(team.getId());
            }
        }
        
        dao.close();
    }
}
