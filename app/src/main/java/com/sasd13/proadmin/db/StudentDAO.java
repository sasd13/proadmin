package com.sasd13.proadmin.db;

import com.sasd13.proadmin.bean.member.Student;

public interface StudentDAO {

    String STUDENT_TABLE_NAME = "students";

    String STUDENT_ID = "student_id";
    String STUDENT_NUMBER = "student_number";
    String STUDENT_ACADEMICLEVEL = "student_academiclevel";
    String STUDENT_FIRSTNAME = "student_firstname";
    String STUDENT_LASTNAME = "student_lastname";
    String STUDENT_EMAIL = "student_email";

    long insert(Student student);

    void update(Student student);

    Student select(long id);

    Student selectByNumber(String number);
}
