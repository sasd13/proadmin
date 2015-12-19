package com.sasd13.proadmin.core.db;

import java.util.List;

import com.sasd13.proadmin.core.bean.member.Teacher;

public interface TeacherDAO {

    String TEACHER_TABLE_NAME = "teachers";

    String TEACHER_ID = "teacher_id";
    String TEACHER_NUMBER = "teacher_number";
    String TEACHER_FIRSTNAME = "teacher_firstname";
    String TEACHER_LASTNAME = "teacher_lastname";
    String TEACHER_EMAIL = "teacher_email";
    String TEACHER_PASSWORD = "teacher_password";

    long insert(Teacher teacher);

    void update(Teacher teacher);
    
    void delete(long id);

    Teacher select(long id);

    Teacher selectByNumber(String number);

    List<Teacher> selectByEmail(String email);
    
    List<Teacher> selectAll();
}
