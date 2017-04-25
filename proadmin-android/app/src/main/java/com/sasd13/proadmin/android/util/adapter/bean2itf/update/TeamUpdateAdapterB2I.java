package com.sasd13.proadmin.android.util.adapter.bean2itf.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.update.TeamUpdate;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.team.CoreInfo;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

public class TeamUpdateAdapterB2I implements IAdapter<TeamUpdate, TeamBean> {

    @Override
    public TeamBean adapt(TeamUpdate s) {
        TeamBean t = new TeamBean();

        Id id = new Id();
        id.setId(s.getNumber());
        t.setId(id);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setNumber(s.getWrapped().getNumber());
        coreInfo.setName(s.getWrapped().getName());
        t.setCoreInfo(coreInfo);

        return t;
    }
}
