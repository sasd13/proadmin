package proadmin.data.dao.accessor;

import proadmin.content.ListStudents;
import proadmin.content.Student;

/**
 * Created by Samir on 11/06/2015.
 */
public interface StudentAccessor {

    void insertStudent(Student student, String squadId);

    void updateStudent(Student student);

    void deleteStudentFromSquad(String studentId, String squadId);

    Student selectStudent(String studentIdOrEmail);

    ListStudents selectStudentsOfSquad(String squadId);
}
