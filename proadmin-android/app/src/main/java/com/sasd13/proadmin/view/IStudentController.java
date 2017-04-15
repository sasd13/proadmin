package com.sasd13.proadmin.view;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IStudentController extends IController {

    void actionNewStudent(Team team);

    void actionCreateStudent(StudentTeam studentTeam);

    void actionShowStudent(StudentTeam studentTeam);

    void actionUpdateStudent(StudentUpdateWrapper studentUpdateWrapper);
}
