package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.model.Team;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class NewTeamBuilder implements IBuilder<Team> {

    @Override
    public Team build() {
        Team team = new Team();

        return team;
    }
}
