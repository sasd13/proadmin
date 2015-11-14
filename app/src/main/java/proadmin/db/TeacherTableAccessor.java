package proadmin.db;

import proadmin.beans.Teacher;

public interface TeacherTableAccessor {

    String TABLE_NAME = "teachers";

    String TEACHER_ID = "teacher_id";
    String TEACHER_FIRSTNAME = "teacher_firstname";
    String TEACHER_LASTNAME = "teacher_lastname";
    String TEACHER_EMAIL = "teacher_email";
    String TEACHER_PASSWORD = "teacher_password";

    void insert(Teacher teacher);

    void update(Teacher teacher);

    Teacher select(String id);

    Teacher selectByEmail(String email);
}
