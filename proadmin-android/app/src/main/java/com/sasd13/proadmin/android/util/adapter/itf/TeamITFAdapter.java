package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.TeamAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.TeamAdapterM2I;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class TeamITFAdapter extends ITFAdapter<Team, TeamBean> {

    public TeamITFAdapter() {
        super(new TeamAdapterI2M(), new TeamAdapterM2I());
    }
}
