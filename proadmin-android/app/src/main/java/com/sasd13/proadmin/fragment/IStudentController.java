package com.sasd13.proadmin.fragment;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.Team;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IStudentController extends IController {

    void newStudent(Team team);

    void showStudent(Student student);

    void createStudent(Student student, Team team);

    void updateStudent(Student student, Student studentToUpdate);
}
