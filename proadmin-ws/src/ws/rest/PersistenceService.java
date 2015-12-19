package ws.rest;

import java.lang.reflect.Array;
import java.util.Map;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

@SuppressWarnings("rawtypes")
public class PersistenceService {

	private static Class mClass;
	private static DAO dao = JDBCDAO.getInstance();
	
	public static void setBeanClass(Class mClass) {
		PersistenceService.mClass = mClass;
	}
	
	public static String create(Object object) {		
		long id = 0;
		
		dao.open();
		performCreate(object);
    	dao.close();
		
		return String.valueOf(id);
	}
	
	private static long performCreate(Object object) {
		if ("Project".equals(mClass.getSimpleName())) {
			return dao.insertProject((Project) object);
		} else if ("Report".equals(mClass.getSimpleName())) {
			return dao.insertReport((Report) object);
		} else if ("Running".equals(mClass.getSimpleName())) {
			return dao.insertRunning((Running) object);
		} else if ("Student".equals(mClass.getSimpleName())) {
			return dao.insertStudent((Student) object);
		} else if ("Teacher".equals(mClass.getSimpleName())) {
			return dao.insertTeacher((Teacher) object);
		} else if ("Team".equals(mClass.getSimpleName())) {
			return dao.insertTeam((Team) object);
		} else {
			return 0;
		}
	}
	
	public static String read(Map<String, String[]> mapParameters) {
		String respData = null;
		
		dao.open();
		/*
		Object object = select(Long.parseLong(paramId));
		respData = gson.toJson(object);
		*/
		dao.close();
		
		return respData;
	}
	
	private static Object performRead(long id) {
		if ("Project".equals(mClass.getSimpleName())) {
			return dao.selectProject(id);
		} else if ("Report".equals(mClass.getSimpleName())) {
			return dao.selectReport(id);
		} else if ("Running".equals(mClass.getSimpleName())) {
			return dao.selectRunning(id);
		} else if ("Student".equals(mClass.getSimpleName())) {
			return dao.selectStudent(id);
		} else if ("Teacher".equals(mClass.getSimpleName())) {
			return dao.selectTeacher(id);
		} else if ("Team".equals(mClass.getSimpleName())) {
			return dao.selectTeam(id);
		} else {
			return null;
		}
	}
	
	public static Object readAll() {
		dao.open();
		Object objects = performReadAll();
		dao.close();
		
		return objects;
	}
	
	private static Object performReadAll() {
		if ("Project".equals(mClass.getSimpleName())) {
			return dao.selectAllProjects();
		} else if ("Report".equals(mClass.getSimpleName())) {
			return dao.selectAllReports();
		} else if ("Running".equals(mClass.getSimpleName())) {
			return dao.selectAllRunnings();
		} else if ("Student".equals(mClass.getSimpleName())) {
			return dao.selectAllStudents();
		} else if ("Teacher".equals(mClass.getSimpleName())) {
			return dao.selectAllTeachers();
		} else if ("Team".equals(mClass.getSimpleName())) {
			return dao.selectAllTeams();
		} else {
			return null;
		}
	}
	
	public void update(Object object) {
		dao.open();
		
		try {
			if (object.getClass().isArray()) {
				for (int i=0; i<Array.getLength(object); i++) {
					performUpdate(Array.get(object, i));
				}
			} else {
				performUpdate(object);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		dao.close();
	}
	
	private static void performUpdate(Object object) {
		if ("Project".equals(mClass.getSimpleName())) {
			dao.updateProject((Project) object);
		} else if ("Report".equals(mClass.getSimpleName())) {
			dao.updateReport((Report) object);
		} else if ("Running".equals(mClass.getSimpleName())) {
			dao.updateRunning((Running) object);
		} else if ("Student".equals(mClass.getSimpleName())) {
			dao.updateStudent((Student) object);
		} else if ("Teacher".equals(mClass.getSimpleName())) {
			dao.updateTeacher((Teacher) object);
		} else if ("Team".equals(mClass.getSimpleName())) {
			dao.updateTeam((Team) object);
		}
	}
	
	public static void delete(Map<String, String[]> mapParameters) {
		dao.open();
		/*
		delete(Long.parseLong(paramId));
		*/
		dao.close();
	}
	
	private static void performDelete(long id) {
		if ("Project".equals(mClass.getSimpleName())) {
			dao.deleteProject(id);
		} else if ("Report".equals(mClass.getSimpleName())) {
			dao.deleteReport(id);
		} else if ("Running".equals(mClass.getSimpleName())) {
			dao.deleteRunning(id);
		} else if ("Student".equals(mClass.getSimpleName())) {
			dao.deleteStudent(id);
		} else if ("Teacher".equals(mClass.getSimpleName())) {
			dao.deleteTeacher(id);
		} else if ("Team".equals(mClass.getSimpleName())) {
			dao.deleteTeam(id);
		}
	}
}
