package proadmin.db;

import proadmin.beans.Student;

public interface StudentTableAccessor {

    String STUDENT_TABLE_NAME = "students";

    String STUDENT_ID = "student_id";
    String STUDENT_NUMBER = "student_number";
    String STUDENT_FIRSTNAME = "student_firstname";
    String STUDENT_LASTNAME = "student_lastname";
    String STUDENT_EMAIL = "student_email";
    String STUDENT_ACADEMICLEVEL = "student_academiclevel";

    long insert(Student student);

    void update(Student student);

    Student select(long id);

    Student selectByNumber(String number);
}
