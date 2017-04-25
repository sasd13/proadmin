package com.sasd13.proadmin.android.bean.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.android.bean.Team;

public class TeamUpdate implements IUpdateWrapper<Team> {

    private Team team;
    private String number;

    @Override
    public Team getWrapped() {
        return team;
    }

    @Override
    public void setWrapped(Team team) {
        this.team = team;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
