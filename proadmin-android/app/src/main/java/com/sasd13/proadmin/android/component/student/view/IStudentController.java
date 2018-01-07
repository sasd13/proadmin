package com.sasd13.proadmin.android.component.student.view;

import com.sasd13.proadmin.android.component.IController;
import com.sasd13.proadmin.android.model.Student;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.model.Team;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IStudentController extends IController {

    void actionNewStudent(Team team);

    void actionCreateStudent(StudentTeam studentTeam);

    void actionShowStudent(StudentTeam studentTeam);

    void actionUpdateStudent(Student student);
}
