package proadmin.data.dao.accessor;

import proadmin.data.db.sqlite.SQLiteDAO;

/**
 * Created by Samir on 11/06/2015.
 */
public class DataAccessorFactory {

    protected DataAccessorFactory() {}

    public static DataAccessor get(String type) throws DataAccessorException {
        if (type.equalsIgnoreCase("SQLITE")) {
            return SQLiteDAO.getInstance();
        } else {
            throw new DataAccessorException("Data accessor not found");
        }
    }
}
