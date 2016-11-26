package com.sasd13.proadmin.ws.wrapper.member;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.ws.wrapper.IReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class TeamReadWrapper implements IReadWrapper<List<Team>> {

    private List<Team> teams;

    public TeamReadWrapper(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public List<Team> getWrapped() {
        return teams;
    }
}
