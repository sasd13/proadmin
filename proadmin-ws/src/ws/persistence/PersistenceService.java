package ws.persistence;

import java.util.List;

import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PersistenceService {
	
	private static DAO dao = JDBCDAO.getInstance();
	
	public static long create(Object object) {
		long id = 0;
		
		try {
			dao.open();
			
			id = dao.getEntityDAO(object.getClass()).insert(object);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
		return id;
	}
	
	public static Object read(long id, Class mClass) {
		Object object = null;
		
		try {
			dao.open();
			
			object = dao.getEntityDAO(mClass).select(id);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
		return object;
	}
	
	public static List readAll(Class mClass) {
		List list = null;
		
		try {
			dao.open();
			
			list = dao.getEntityDAO(mClass).selectAll();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
		return list;
	}
	
	public static void update(Object object) {
		try {
			dao.open();
			
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
		} finally {
			dao.close();
		}
	}
	
	private static void performUpdate(Object object) {
		dao.getEntityDAO(object.getClass()).update(object);
	}
	
	public static void delete(long id, Class mClass) {
		try {
			dao.open();
			
			dao.getEntityDAO(mClass).delete(id);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
	}
}
