package proadmin.db;

import proadmin.db.sqlite.SQLiteDAO;

public class DAOFactory {

    private DAOFactory() {}

    public static DAO get() {
        return get(SQLiteDAO.class);
    }

    public static DAO get(Class daoClass) {
        return SQLiteDAO.getInstance();
    }
}
