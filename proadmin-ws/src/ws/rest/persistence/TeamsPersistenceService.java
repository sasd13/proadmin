package ws.rest.persistence;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

public class TeamsPersistenceService {

	private static Gson gson = new GsonBuilder().create();
	private static DAO dao = JDBCDAO.getInstance();
	
	public static String create(String reqData, MimeType mimeType) {		
		Team team = null;
		
		if (mimeType == MimeType.JSON) {
			team = gson.fromJson(reqData, Team.class);
		}
		
		long id = 0;
		
		if (team != null) {
			dao.open();
	    	id = dao.insertTeam(team);
	    	dao.close();
		}
		
		return String.valueOf(id);
	}
	
	public static String read(String paramId) {
		String respData = null;
		
		dao.open();
		
		Team team = dao.selectTeam(Long.parseLong(paramId));
		respData = gson.toJson(team);
		
		dao.close();
		
		return respData;
	}
	
	public static String readAll() {
		dao.open();
		List<Team> teams = dao.selectAllTeams();
		dao.close();
        
        return gson.toJson(teams);
	}
	
	public static void update(String reqData, MimeType mimeType) {
		Team team = null;
		Team[] teams = null;
		
		if (mimeType == MimeType.JSON) {
			if (reqData.startsWith("{") && reqData.endsWith("}")) {
				team = gson.fromJson(reqData, Team.class);
			} else if (reqData.startsWith("[") && reqData.endsWith("]")) {
				teams = gson.fromJson(reqData, Team[].class);
			}
		}
		
		if (team != null || teams != null) {
			dao.open();
			if (team != null) {
				dao.updateTeam(team);
			} else {
				for (Team o : teams) {
					dao.updateTeam(o);
				}
			}
			dao.close();
		}
	}
	
	public static void delete(String paramId) {
		dao.open();
		dao.deleteTeam(Long.parseLong(paramId));
		dao.close();
	}
	
	public static void deleteAll() {
		dao.open();
		
		List<Team> teams = dao.selectAllTeams();
		for (Team team : teams) {
			dao.deleteTeam(team.getId());
		}
		
		dao.close();
	}
}
