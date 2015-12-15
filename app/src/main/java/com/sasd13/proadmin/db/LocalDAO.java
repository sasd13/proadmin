package com.sasd13.proadmin.db;

import android.content.Context;

import com.sasd13.proadmin.core.db.DAO;

/**
 * Created by Samir on 15/12/2015.
 */
public abstract class LocalDAO extends DAO {

    public abstract void init(Context context);
}
