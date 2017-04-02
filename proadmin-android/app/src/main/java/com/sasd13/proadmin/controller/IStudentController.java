package com.sasd13.proadmin.controller;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IStudentController extends IController {

    void listStudents(Team team);

    void newStudent(Team team);

    void showStudent(Student student);

    void createStudent(Student student, Team team);

    void updateStudent(Student student, Student studentToUpdate);

    void deleteStudentTeams(StudentTeam[] studentTeams);
}
