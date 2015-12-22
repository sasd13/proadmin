package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class SQLiteEntityDAO<T> {

    protected SQLiteDatabase db;

    public void setDB(SQLiteDatabase db) { this.db = db; }

    protected abstract ContentValues getContentValues(T t);

    protected abstract T getCursorValues(Cursor cursor);
}
