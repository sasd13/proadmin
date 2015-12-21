package ws.persistence;

import java.util.List;

import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PersistenceService {
	
	private static DAO dao = JDBCDAO.getInstance();
	
	public static long create(Object object) {
		long id = 0;
		
		dao.open();
		
		try {
			id = dao.getEntityDAO(object.getClass()).insert(object);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		dao.close();
		
		return id;
	}
	
	public static Object read(long id, Class mClass) {
		Object object = null;
		
		dao.open();
		
		try {
			object = dao.getEntityDAO(mClass).select(id);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		dao.close();
		
		return object;
	}
	
	public static List readAll(Class mClass) {
		List list = null;
		
		dao.open();
		
		try {
			list = dao.getEntityDAO(mClass).selectAll();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		dao.close();
		
		return list;
	}
	
	public static void update(Object object) {
		dao.open();
		
		try {
			if (object.getClass().isArray()) {
				for (Object o : (Object[]) object) {
					performUpdate(o);
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
			dao.getEntityDAO(object.getClass()).update(object);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(long id, Class mClass) {
		dao.open();
		
		try {
			dao.getEntityDAO(mClass).delete(id);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		dao.close();
	}
}
