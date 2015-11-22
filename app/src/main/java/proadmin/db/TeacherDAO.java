package proadmin.db;

import proadmin.bean.member.Teacher;

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

    Teacher select(long id);

    Teacher selectByNumber(String number);

    Teacher selectByEmail(String email);

    boolean containsByNumber(String number);

    boolean containsByEmail(String email);
}
