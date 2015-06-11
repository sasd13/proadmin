package proadmin.manager.data;

import android.content.Context;

/**
 * Created by Samir on 11/06/2015.
 */
public class Data {

    private static Data instance = null;

    private DataAccessor dao;

    private Data(Context context) {
        try {
            this.dao = DataAccessorFactory.get(DataAccessorFactory.TYPE_SQLITE);
            this.dao.create(context);
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Data getInstance(Context context) {
        if (instance == null) {
            instance = new Data(context);
        }

        return instance;
    }

    public DataAccessor getDao() {
        return this.dao;
    }

    public void setDao(DataAccessor dao) {
        this.dao = dao;
    }
}
