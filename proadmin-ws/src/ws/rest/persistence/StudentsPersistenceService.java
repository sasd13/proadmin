package ws.rest.persistence;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

public class StudentsPersistenceService {

	private static Gson gson = new GsonBuilder().create();
	private static DAO dao = JDBCDAO.getInstance();
	
	public static String create(String reqData, MimeType mimeType) {		
		Student student = null;
		
		if (mimeType == MimeType.JSON) {
			student = gson.fromJson(reqData, Student.class);
		}
		
		long id = 0;
		
		if (student != null) {
			dao.open();
	    	id = dao.insertStudent(student);
	    	dao.close();
		}
		
		return String.valueOf(id);
	}
	
	public static String read(String paramId) {
		String respData = null;
		
		dao.open();
		
		Student student = dao.selectStudent(Long.parseLong(paramId));
		respData = gson.toJson(student);
		
		dao.close();
		
		return respData;
	}
	
	public static String readAll() {
		dao.open();
		List<Student> students = dao.selectAllStudents();
		dao.close();
        
        return gson.toJson(students);
	}
	
	public static void update(String reqData, MimeType mimeType) {
		Student student = null;
		Student[] students = null;
		
		if (mimeType == MimeType.JSON) {
			if (reqData.startsWith("{") && reqData.endsWith("}")) {
				student = gson.fromJson(reqData, Student.class);
			} else if (reqData.startsWith("[") && reqData.endsWith("]")) {
				students = gson.fromJson(reqData, Student[].class);
			}
		}
		
		if (student != null || students != null) {
			dao.open();
			if (student != null) {
				dao.updateStudent(student);
			} else {
				for (Student o : students) {
					dao.updateStudent(o);
				}
			}
			dao.close();
		}
	}
	
	public static void delete(String paramId) {
		dao.open();
		dao.deleteStudent(Long.parseLong(paramId));
		dao.close();
	}
	
	public static void deleteAll() {
		dao.open();
		
		List<Student> students = dao.selectAllStudents();
		for (Student student : students) {
			dao.deleteStudent(student.getId());
		}
		
		dao.close();
	}
}
