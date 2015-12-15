package com.sasd13.proadmin.db;

import com.sasd13.proadmin.db.sqlite.SQLiteDAO;

public class DAOFactory {

    public static LocalDAO make() {
        return make(SQLiteDAO.class);
    }

    public static LocalDAO make(Class dao) {
        return SQLiteDAO.getInstance();
    }
}
