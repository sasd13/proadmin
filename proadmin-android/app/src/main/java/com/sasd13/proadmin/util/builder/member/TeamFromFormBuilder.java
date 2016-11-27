package com.sasd13.proadmin.util.builder.member;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.gui.form.TeamForm;
import com.sasd13.proadmin.util.builder.IBuilderFromForm;

/**
 * Created by ssaidali2 on 22/11/2016.
 */

public class TeamFromFormBuilder implements IBuilderFromForm<Team> {

    private TeamForm teamForm;

    public TeamFromFormBuilder(TeamForm teamForm) {
        this.teamForm = teamForm;
    }

    @Override
    public Team build() throws FormException {
        Team teamFromForm = new Team(teamForm.getNumber());

        return teamFromForm;
    }
}
