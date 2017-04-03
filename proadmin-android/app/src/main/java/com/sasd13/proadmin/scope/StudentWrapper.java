package com.sasd13.proadmin.scope;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.Team;

import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class StudentWrapper extends Observable {

    private Student student;
    private Team team;

    public StudentWrapper(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;

        setChanged();
        notifyObservers();
    }
}
