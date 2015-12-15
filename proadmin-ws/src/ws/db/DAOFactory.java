package ws.db;

import ws.db.mysql.MySQLDAO;

import com.sasd13.proadmin.core.db.DAO;

public class DAOFactory {

	public static DAO make() {
		return make("MYSQL");
	}
	
	public static DAO make(String dao) {
		if ("MYSQL".equalsIgnoreCase(dao)) {
			return MySQLDAO.getInstance();
		}
		
		return null;
	}
}
