package proadmin.db.accessor;

import proadmin.content.Id;
import proadmin.content.ListStudents;
import proadmin.content.Student;

/**
 * Created by Samir on 11/06/2015.
 */
public interface StudentAccessor {

    boolean insertStudent(Student student, Id squadId);

    void updateStudent(Student student);

    void deleteStudent(Id studentId);

    void deleteStudentFromSquad(Id studentId, Id squadId);

    Student selectStudent(Id studentId);

    ListStudents selectStudents(Id squadId);
}
