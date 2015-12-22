package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.javaex.db.EntityDAO;

import java.util.ArrayList;
import java.util.List;

public abstract class SQLiteEntityDAO<T> implements EntityDAO<T> {

    protected SQLiteDatabase db;

    public void setDB(SQLiteDatabase db) {
        this.db = db;
    }

    protected abstract ContentValues getContentValues(T t);

    protected abstract T getCursorValues(Cursor cursor);

    protected long executeInsert(String table, T t) {
        long id = db.insert(table, null, getContentValues(t));

        return (id <= 0) ? 0 : id;
    }

    protected void executeUpdate(String table, T t, String columnId, long id) {
        db.update(table, getContentValues(t), columnId + " = ?", new String[]{String.valueOf(id)});
    }

    protected void executeDelete(String table, String columnId, long id) {
        ContentValues values = new ContentValues();

        values.put(DELETED, true);

        db.update(table, values, columnId + " = ?", new String[]{String.valueOf(id)});
    }

    protected T executeSelectById(String query, long id) {
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        return executeSelectSingleResult(cursor);
    }

    protected List<T> executeSelectAll(String query) {
        Cursor cursor = db.rawQuery(query, null);

        return executeSelectMultiResult(cursor);
    }

    protected T executeSelectSingleResult(Cursor cursor) {
        List<T> list = executeSelectMultiResult(cursor);

        return list.isEmpty() ? null : list.get(0);
    }

    protected List<T> executeSelectMultiResult(Cursor cursor) {
        List<T> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
