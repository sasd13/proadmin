package proadmin.db;

import proadmin.bean.member.Teacher;

public interface TeacherTableAccessor {

    String TEACHER_TABLE_NAME = "teachers";

    String TEACHER_ID = "teacher_id";
    String TEACHER_FIRSTNAME = "teacher_firstname";
    String TEACHER_LASTNAME = "teacher_lastname";
    String TEACHER_EMAIL = "teacher_email";
    String TEACHER_PASSWORD = "teacher_password";
    String TEACHER_DELETED = "teacher_deleted";

    long insert(Teacher teacher);

    void update(Teacher teacher);

    void delete(long id);

    Teacher select(long id);

    Teacher selectByEmail(String email);
}
