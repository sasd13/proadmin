package proadmin.db.accessor;

import proadmin.content.Id;
import proadmin.content.Teacher;

/**
 * Created by Samir on 11/06/2015.
 */
public interface TeacherAccessor {

    boolean insertTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Id teacherId);

    Teacher selectTeacher(Id teacherId);

    Teacher selectTeacher(String email);
}
