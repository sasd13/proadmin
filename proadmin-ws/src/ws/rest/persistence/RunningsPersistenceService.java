package ws.rest.persistence;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

public class RunningsPersistenceService {

	private static Gson gson = new GsonBuilder().create();
	private static DAO dao = JDBCDAO.getInstance();
	
	public static String create(String reqData, MimeType mimeType) {		
		Running running = null;
		
		if (mimeType == MimeType.JSON) {
			running = gson.fromJson(reqData, Running.class);
		}
		
		long id = 0;
		
		if (running != null) {
			dao.open();
	    	id = dao.insertRunning(running);
	    	dao.close();
		}
		
		return String.valueOf(id);
	}
	
	public static String read(String paramId) {
		String respData = null;
		
		dao.open();
		
		Running running = dao.selectRunning(Long.parseLong(paramId));
		respData = gson.toJson(running);
		
		dao.close();
		
		return respData;
	}
	
	public static String readAll() {
		dao.open();
		List<Running> runnings = dao.selectAllRunnings();
		dao.close();
        
        return gson.toJson(runnings);
	}
	
	public static void update(String reqData, MimeType mimeType) {
		Running running = null;
		Running[] runnings = null;
		
		if (mimeType == MimeType.JSON) {
			if (reqData.startsWith("{") && reqData.endsWith("}")) {
				running = gson.fromJson(reqData, Running.class);
			} else if (reqData.startsWith("[") && reqData.endsWith("]")) {
				runnings = gson.fromJson(reqData, Running[].class);
			}
		}
		
		if (running != null || runnings != null) {
			dao.open();
			if (running != null) {
				dao.updateRunning(running);
			} else {
				for (Running o : runnings) {
					dao.updateRunning(o);
				}
			}
			dao.close();
		}
	}
	
	public static void delete(String paramId) {
		dao.open();
		dao.deleteRunning(Long.parseLong(paramId));
		dao.close();
	}
	
	public static void deleteAll() {
		dao.open();
		
		List<Running> runnings = dao.selectAllRunnings();
		for (Running running : runnings) {
			dao.deleteRunning(running.getId());
		}
		
		dao.close();
	}
}
