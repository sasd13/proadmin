package com.sasd13.proadmin.android.util.adapter.itf2bean.v2;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

public class TeamAdapterI2B implements IAdapter<TeamBean, Team> {

    @Override
    public Team adapt(TeamBean s) {
        Team t = new Team();

        t.setId(Long.valueOf(s.getId().getId()));
        t.setNumber(s.getCoreInfo().getNumber());
        t.setName(s.getCoreInfo().getName());

        return t;
    }
}
