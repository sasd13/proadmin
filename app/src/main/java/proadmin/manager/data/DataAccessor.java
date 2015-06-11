package proadmin.manager.data;

import android.content.Context;

/**
 * Created by Samir on 11/06/2015.
 */
public interface DataAccessor {

    void create(Context context);
    void open();
    void close();
}
