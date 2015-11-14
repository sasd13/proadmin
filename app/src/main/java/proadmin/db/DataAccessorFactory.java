package proadmin.db;

import proadmin.db.sqlite.SQLiteDAO;

public class DataAccessorFactory {

    private DataAccessorFactory() {}

    public static DataAccessor get() {
        return get("SQLITE");
    }

    public static DataAccessor get(String dbType) {
        if ("SQLITE".equalsIgnoreCase(dbType)) {
            return SQLiteDAO.getInstance();
        }

        return null;
    }
}
