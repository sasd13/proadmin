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
@WebServlet("/teams")
public class TeamsRestWebService extends HttpServlet {

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
            Team team = dao.selectTeam(id);
            
            jsonData = gson.toJson(team);
        } else {
            List<Team> teams = dao.selectAllTeams();
            
            jsonData = gson.toJson(teams);
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
        	Team team = gson.fromJson(jsonData, Team.class);
        	
        	DAO dao = JDBCDAO.getInstance();
            
            dao.open();
        	id = dao.insertTeam(team);
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
        	Team team = gson.fromJson(jsonData, Team.class);
        	
        	dao.updateTeam(team);
        } else if (jsonData.startsWith("[") && jsonData.endsWith("]")) {
        	Team[] teams = gson.fromJson(jsonData, Team[].class);
        	
        	for (Team team : teams) {
        		dao.updateTeam(team);
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
