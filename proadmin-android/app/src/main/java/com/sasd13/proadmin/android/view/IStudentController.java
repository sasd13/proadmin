package com.sasd13.proadmin.android.view;

import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.StudentUpdate;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IStudentController extends IController {

    void actionNewStudent(Team team);

    void actionCreateStudent(StudentTeam studentTeam);

    void actionShowStudent(StudentTeam studentTeam);

    void actionUpdateStudent(StudentUpdate studentUpdate);
}
