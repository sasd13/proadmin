package ws.persistence;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

@SuppressWarnings("rawtypes")
public class DataPersistenceService {
	
	private static DAO dao = JDBCDAO.getInstance();
	
	public static long create(Object object) {		
		long id = 0;
		
		dao.open();
		id = performCreate(object);
		dao.close();
		
		return id;
	}
	
	private static long performCreate(Object object) {
		long id = 0;
		
		try {
			if ("Project".equals(object.getClass().getSimpleName())) {
				return dao.insertProject((Project) object);
			} else if ("Report".equals(object.getClass().getSimpleName())) {
				return dao.insertReport((Report) object);
			} else if ("Running".equals(object.getClass().getSimpleName())) {
				return dao.insertRunning((Running) object);
			} else if ("Student".equals(object.getClass().getSimpleName())) {
				return dao.insertStudent((Student) object);
			} else if ("Teacher".equals(object.getClass().getSimpleName())) {
				return dao.insertTeacher((Teacher) object);
			} else if ("Team".equals(object.getClass().getSimpleName())) {
				return dao.insertTeam((Team) object);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static Object read(long id, Class mClass) {
		Object object = null;
		
		dao.open();
		object = performRead(id, mClass);
		dao.close();
		
		return object;
	}
	
	private static Object performRead(long id, Class mClass) {
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
	
	public static List readAll(Class mClass) {
		List list = null;
		
		dao.open();
		list = performReadAll(mClass);
		dao.close();
		
		return list;
	}
	
	private static List performReadAll(Class mClass) {
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
			return new ArrayList<>();
		}
	}
	
	public static void update(Object object) {
		dao.open();
		
		try {
			if (object.getClass().isArray()) {
				for (int i=0; i<Array.getLength(object); i++) {
					performUpdate(Array.get(object, i));
				}
			} else if (object instanceof Iterable) {
				for (Object o : (Iterable) object) {
					performUpdate(o);
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
		try {
			if ("Project".equals(object.getClass().getSimpleName())) {
				dao.updateProject((Project) object);
			} else if ("Report".equals(object.getClass().getSimpleName())) {
				dao.updateReport((Report) object);
			} else if ("Running".equals(object.getClass().getSimpleName())) {
				dao.updateRunning((Running) object);
			} else if ("Student".equals(object.getClass().getSimpleName())) {
				dao.updateStudent((Student) object);
			} else if ("Teacher".equals(object.getClass().getSimpleName())) {
				dao.updateTeacher((Teacher) object);
			} else if ("Team".equals(object.getClass().getSimpleName())) {
				dao.updateTeam((Team) object);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(long id, Class mClass) {
		dao.open();
		performDelete(id, mClass);
		dao.close();
	}
	
	private static void performDelete(long id, Class mClass) {
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
