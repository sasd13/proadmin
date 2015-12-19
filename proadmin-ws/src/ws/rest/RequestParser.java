package ws.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

@SuppressWarnings({"rawtypes", "unchecked"})
public class RequestParser<T> {

	private Class mClass, mTabClass;
	private Gson gson;
	private DAO dao;
	
	public RequestParser(Class mClass, Class mTabClass) {
		this.mClass = mClass;
		this.mTabClass = mTabClass;
		this.gson = new GsonBuilder().create();
		this.dao = JDBCDAO.getInstance();
	}
	
	public String create(String reqData, MimeType mimeType) {		
		T t = null;
		
		if (mimeType == MimeType.JSON) {
			t = (T) gson.fromJson(reqData, this.mClass);
		}
		
		long id = 0;
		
		if (t != null) {
			dao.open();
			insert(t);
	    	dao.close();
		}
		
		return String.valueOf(id);
	}
	
	private long insert(T t) {
		if ("Project".equals(this.mClass.getSimpleName())) {
			return dao.insertProject((Project) t);
		} else if ("Report".equals(this.mClass.getSimpleName())) {
			return dao.insertReport((Report) t);
		} else if ("Running".equals(this.mClass.getSimpleName())) {
			return dao.insertRunning((Running) t);
		} else if ("Student".equals(this.mClass.getSimpleName())) {
			return dao.insertStudent((Student) t);
		} else if ("Teacher".equals(this.mClass.getSimpleName())) {
			return dao.insertTeacher((Teacher) t);
		} else if ("Team".equals(this.mClass.getSimpleName())) {
			return dao.insertTeam((Team) t);
		} else {
			return 0;
		}
	}
	
	public String read(String[] params) {
		String respData = null;
		
		dao.open();
		
		Object object = select(Long.parseLong(paramId));
		respData = gson.toJson(object);
		
		dao.close();
		
		return respData;
	}
	
	private Object select(long id) {
		if ("Project".equals(this.mClass.getSimpleName())) {
			return dao.selectProject(id);
		} else if ("Report".equals(this.mClass.getSimpleName())) {
			return dao.selectReport(id);
		} else if ("Running".equals(this.mClass.getSimpleName())) {
			return dao.selectRunning(id);
		} else if ("Student".equals(this.mClass.getSimpleName())) {
			return dao.selectStudent(id);
		} else if ("Teacher".equals(this.mClass.getSimpleName())) {
			return dao.selectTeacher(id);
		} else if ("Team".equals(this.mClass.getSimpleName())) {
			return dao.selectTeam(id);
		} else {
			return null;
		}
	}
	
	public String readAll() {
		dao.open();
		Object objects = selectAll();
		dao.close();
        
        return gson.toJson(objects);
	}
	
	private Object selectAll() {
		if ("Project".equals(this.mClass.getSimpleName())) {
			return dao.selectAllProjects();
		} else if ("Report".equals(this.mClass.getSimpleName())) {
			return dao.selectAllReports();
		} else if ("Running".equals(this.mClass.getSimpleName())) {
			return dao.selectAllRunnings();
		} else if ("Student".equals(this.mClass.getSimpleName())) {
			return dao.selectAllStudents();
		} else if ("Teacher".equals(this.mClass.getSimpleName())) {
			return dao.selectAllTeachers();
		} else if ("Team".equals(this.mClass.getSimpleName())) {
			return dao.selectAllTeams();
		} else {
			return null;
		}
	}
	
	public void update(String reqData, MimeType mimeType) {
		T t = null;
		T[] ts = null;
		
		if (mimeType == MimeType.JSON) {
			if (reqData.startsWith("{") && reqData.endsWith("}")) {
				t = (T) gson.fromJson(reqData, this.mClass);
			} else if (reqData.startsWith("[") && reqData.endsWith("]")) {
				ts = (T[]) gson.fromJson(reqData, this.mTabClass);
			}
		}
		
		if (t != null || t != null) {
			dao.open();
			if (t != null) {
				update(t);
			} else {
				for (T o : ts) {
					update(o);
				}
			}
			dao.close();
		}
	}
	
	private void update(T t) {
		if ("Project".equals(this.mClass.getSimpleName())) {
			dao.updateProject((Project) t);
		} else if ("Report".equals(this.mClass.getSimpleName())) {
			dao.updateReport((Report) t);
		} else if ("Running".equals(this.mClass.getSimpleName())) {
			dao.updateRunning((Running) t);
		} else if ("Student".equals(this.mClass.getSimpleName())) {
			dao.updateStudent((Student) t);
		} else if ("Teacher".equals(this.mClass.getSimpleName())) {
			dao.updateTeacher((Teacher) t);
		} else if ("Team".equals(this.mClass.getSimpleName())) {
			dao.updateTeam((Team) t);
		}
	}
	
	public void delete(String[] params) {
		dao.open();
		delete(Long.parseLong(paramId));
		dao.close();
	}
	
	private void delete(long id) {
		if ("Project".equals(this.mClass.getSimpleName())) {
			dao.deleteProject(id);
		} else if ("Report".equals(this.mClass.getSimpleName())) {
			dao.deleteReport(id);
		} else if ("Running".equals(this.mClass.getSimpleName())) {
			dao.deleteRunning(id);
		} else if ("Student".equals(this.mClass.getSimpleName())) {
			dao.deleteStudent(id);
		} else if ("Teacher".equals(this.mClass.getSimpleName())) {
			dao.deleteTeacher(id);
		} else if ("Team".equals(this.mClass.getSimpleName())) {
			dao.deleteTeam(id);
		}
	}
}
