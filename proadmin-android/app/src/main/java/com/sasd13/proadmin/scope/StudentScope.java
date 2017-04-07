package com.sasd13.proadmin.scope;

import com.sasd13.proadmin.bean.member.StudentTeam;

import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class StudentScope extends Observable {

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
