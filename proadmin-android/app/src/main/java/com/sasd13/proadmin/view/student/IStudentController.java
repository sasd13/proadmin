package com.sasd13.proadmin.view.student;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.view.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IStudentController extends IController {

    void createStudent(Student student, Team team);

    void updateStudent(Student student, Student studentToUpdate);
}
