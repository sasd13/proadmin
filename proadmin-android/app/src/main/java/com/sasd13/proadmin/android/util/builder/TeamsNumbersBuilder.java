package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class TeamsNumbersBuilder implements IBuilder<List<String>> {

    private List<Team> teams;

    public TeamsNumbersBuilder(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public List<String> build() {
        List<String> list = new ArrayList<>();

        for (Team team : teams) {
            list.add(team.getNumber());
        }

        return list;
    }
}
