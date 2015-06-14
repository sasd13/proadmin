package proadmin.pattern.dao.accessor;

import proadmin.content.ListStudents;
import proadmin.content.Student;
import proadmin.content.id.SquadId;
import proadmin.content.id.StudentId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface StudentAccessor {

    void insertStudent(Student student, SquadId squadId);

    void updateStudent(Student student);

    void deleteStudent(StudentId studentId);

    void deleteStudentFromSquad(StudentId studentId, SquadId squadId);

    Student selectStudent(StudentId studentId);

    ListStudents selectStudents(SquadId squadId);
}
