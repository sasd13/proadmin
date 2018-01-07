package com.sasd13.proadmin.android.scope;

import com.sasd13.proadmin.android.model.StudentTeam;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class StudentScope extends Scope {

    private StudentTeam studentTeam;

    public StudentTeam getStudentTeam() {
        return studentTeam;
    }

    public void setStudentTeam(StudentTeam studentTeam) {
        this.studentTeam = studentTeam;

        setChanged();
        notifyObservers();
    }
}
