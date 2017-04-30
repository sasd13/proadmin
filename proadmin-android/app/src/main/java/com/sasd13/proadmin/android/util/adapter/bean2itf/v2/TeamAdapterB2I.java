package com.sasd13.proadmin.android.util.adapter.bean2itf.v2;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.team.CoreInfo;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

public class TeamAdapterB2I implements IAdapter<Team, TeamBean> {

    @Override
    public TeamBean adapt(Team s) {
        TeamBean t = new TeamBean();

        Id id = new Id();
        id.setId(String.valueOf(s.getId()));
        t.setId(id);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setNumber(s.getNumber());
        coreInfo.setName(s.getName());
        t.setCoreInfo(coreInfo);

        return t;
    }
}
