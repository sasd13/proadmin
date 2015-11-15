package proadmin.db;

import proadmin.db.sqlite.SQLiteDAO;

public class DataAccessorFactory {

    private DataAccessorFactory() {}

    public static DataAccessor get() {
        return get(SQLiteDAO.class);
    }

    public static DataAccessor get(Class daoClass) {
        return SQLiteDAO.getInstance();
    }
}
