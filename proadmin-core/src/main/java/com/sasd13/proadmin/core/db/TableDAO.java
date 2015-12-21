package com.sasd13.proadmin.core.db;

import java.util.List;

public interface TableDAO<T> {

    String DELETED = "deleted";

    long insert(T t);

    void update(T t);

    void delete(long id);

    T select(long id);
    
    List<T> selectAll();
}
