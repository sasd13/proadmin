package ws.persistence;

import java.util.List;

import com.sasd13.proadmin.core.db.DAO;

import db.JDBCDAO;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PersistenceService<T> {
	
	private Class mClass;
	private DAO dao = JDBCDAO.getInstance();
	
	public PersistenceService(Class mClass) {
		this.mClass = mClass;
	}
	
	public long create(T t) {
		long id = 0;
		
		try {
			dao.open();
			
			id = dao.getEntityDAO(mClass).insert(t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
		return id;
	}
	
	public T read(long id) {
		T t = null;
		
		try {
			dao.open();
			
			t = (T) dao.getEntityDAO(mClass).select(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
		return t;
	}
	
	public List<T> readAll() {
		List<T> list = null;
		
		try {
			dao.open();
			
			list = dao.getEntityDAO(mClass).selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
		return list;
	}
	
	public void update(T t) {
		try {
			dao.open();
			
			dao.getEntityDAO(mClass).update(t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
	}
	
	public void delete(long id) {
		try {
			dao.open();
			
			dao.getEntityDAO(mClass).delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
	}
}
