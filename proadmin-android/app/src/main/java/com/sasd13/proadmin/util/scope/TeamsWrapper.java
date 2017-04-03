package com.sasd13.proadmin.util.scope;

import com.sasd13.proadmin.bean.member.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class TeamsWrapper extends Observable {

    private List<Team> teams;

    public TeamsWrapper() {
        teams = new ArrayList<>();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;

        setChanged();
        notifyObservers();
    }
}
