package com.sasd13.proadmin.util.builder.member;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Team;

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

        String number;

        for (Team team : teams) {
            number = team.getNumber();

            if (!list.contains(number)) {
                list.add(number);
            }
        }

        return list;
    }
}
