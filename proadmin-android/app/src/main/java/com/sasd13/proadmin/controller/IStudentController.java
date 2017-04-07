package com.sasd13.proadmin.controller;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IStudentController extends IController {

    void actionNewStudent(Team team);

    void actionCreateStudent(StudentTeam studentTeam);

    void actionShowStudent(StudentTeam studentTeam);

    void actionUpdateStudent(Student student, Student studentToUpdate);
}
