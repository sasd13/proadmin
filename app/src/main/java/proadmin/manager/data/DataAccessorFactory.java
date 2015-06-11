package proadmin.manager.data;

import proadmin.db.sqlite.SQLiteDAO;

/**
 * Created by Samir on 11/06/2015.
 */
public class DataAccessorFactory {

    public static final String TYPE_SQLITE = "sqlite";

    private DataAccessorFactory() {}

    public static DataAccessor get(String type) throws DataException {
        switch (type) {
            case TYPE_SQLITE:
                return new SQLiteDAO();
            default:
                throw new DataException("Data accessor not found");
        }
    }
}
