package com.sasd13.proadmin.db;

import com.sasd13.proadmin.core.db.DAO;
import com.sasd13.proadmin.db.sqlite.SQLiteDAO;

public class DAOFactory {

    public static DAO make() {
        return make("SQLITE");
    }

    public static DAO make(String dao) {
        if ("SQLITE".equalsIgnoreCase(dao)) {
            return SQLiteDAO.getInstance();
        }

        return null;
    }
}
