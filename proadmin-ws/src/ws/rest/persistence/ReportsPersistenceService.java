package ws.rest.persistence;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

public class ReportsPersistenceService {

	private static Gson gson = new GsonBuilder().create();
	private static DAO dao = JDBCDAO.getInstance();
	
	public static String create(String reqData, MimeType mimeType) {		
		Report report = null;
		
		if (mimeType == MimeType.JSON) {
			report = gson.fromJson(reqData, Report.class);
		}
		
		long id = 0;
		
		if (report != null) {
			dao.open();
	    	id = dao.insertReport(report);
	    	dao.close();
		}
		
		return String.valueOf(id);
	}
	
	public static String read(String paramId) {
		String respData = null;
		
		dao.open();
		
		Report report = dao.selectReport(Long.parseLong(paramId));
		respData = gson.toJson(report);
		
		dao.close();
		
		return respData;
	}
	
	public static String readAll() {
		dao.open();
		List<Report> reports = dao.selectAllReports();
		dao.close();
        
        return gson.toJson(reports);
	}
	
	public static void update(String reqData, MimeType mimeType) {
		Report report = null;
		Report[] reports = null;
		
		if (mimeType == MimeType.JSON) {
			if (reqData.startsWith("{") && reqData.endsWith("}")) {
				report = gson.fromJson(reqData, Report.class);
			} else if (reqData.startsWith("[") && reqData.endsWith("]")) {
				reports = gson.fromJson(reqData, Report[].class);
			}
		}
		
		if (report != null || reports != null) {
			dao.open();
			if (report != null) {
				dao.updateReport(report);
			} else {
				for (Report o : reports) {
					dao.updateReport(o);
				}
			}
			dao.close();
		}
	}
	
	public static void delete(String paramId) {
		dao.open();
		dao.deleteReport(Long.parseLong(paramId));
		dao.close();
	}
	
	public static void deleteAll() {
		dao.open();
		
		List<Report> reports = dao.selectAllReports();
		for (Report report : reports) {
			dao.deleteReport(report.getId());
		}
		
		dao.close();
	}
}
