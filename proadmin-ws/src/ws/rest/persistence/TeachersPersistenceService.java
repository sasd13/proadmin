package ws.rest.persistence;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

public class TeachersPersistenceService {

	private static Gson gson = new GsonBuilder().create();
	private static DAO dao = JDBCDAO.getInstance();
	
	public static String create(String reqData, MimeType mimeType) {		
		Teacher teacher = null;
		
		if (mimeType == MimeType.JSON) {
			teacher = gson.fromJson(reqData, Teacher.class);
		}
		
		long id = 0;
		
		if (teacher != null) {
			dao.open();
	    	id = dao.insertTeacher(teacher);
	    	dao.close();
		}
		
		return String.valueOf(id);
	}
	
	public static String read(String paramId) {
		String respData = null;
		
		dao.open();
		
		Teacher teacher = dao.selectTeacher(Long.parseLong(paramId));
		respData = gson.toJson(teacher);
		
		dao.close();
		
		return respData;
	}
	
	public static String readAll() {
		dao.open();
		List<Teacher> teachers = dao.selectAllTeachers();
		dao.close();
        
        return gson.toJson(teachers);
	}
	
	public static void update(String reqData, MimeType mimeType) {
		Teacher teacher = null;
		Teacher[] teachers = null;
		
		if (mimeType == MimeType.JSON) {
			if (reqData.startsWith("{") && reqData.endsWith("}")) {
				teacher = gson.fromJson(reqData, Teacher.class);
			} else if (reqData.startsWith("[") && reqData.endsWith("]")) {
				teachers = gson.fromJson(reqData, Teacher[].class);
			}
		}
		
		if (teacher != null || teachers != null) {
			dao.open();
			if (teacher != null) {
				dao.updateTeacher(teacher);
			} else {
				for (Teacher o : teachers) {
					dao.updateTeacher(o);
				}
			}
			dao.close();
		}
	}
	
	public static void delete(String paramId) {
		dao.open();
		dao.deleteTeacher(Long.parseLong(paramId));
		dao.close();
	}
	
	public static void deleteAll() {
		dao.open();
		
		List<Teacher> teachers = dao.selectAllTeachers();
		for (Teacher teacher : teachers) {
			dao.deleteTeacher(teacher.getId());
		}
		
		dao.close();
	}
}
