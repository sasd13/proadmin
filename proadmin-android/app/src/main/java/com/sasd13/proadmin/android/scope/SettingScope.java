package com.sasd13.proadmin.android.scope;

import com.sasd13.proadmin.android.bean.Teacher;

import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class SettingScope extends Observable {

    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;

        setChanged();
        notifyObservers();
    }
}
