package proadmin.data.dao.accessor;

import proadmin.bean.member.Teacher;

/**
 * Created by Samir on 11/06/2015.
 */
public interface TeacherAccessor {

    void insertTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(String teacherId);

    Teacher selectTeacher(String teacherIdOrEmail);
}
