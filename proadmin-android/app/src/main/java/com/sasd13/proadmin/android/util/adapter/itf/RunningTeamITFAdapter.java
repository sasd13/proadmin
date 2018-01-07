package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.RunningTeamAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.RunningTeamAdapterM2I;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class RunningTeamITFAdapter extends ITFAdapter<RunningTeam, RunningTeamBean> {

    public RunningTeamITFAdapter() {
        super(new RunningTeamAdapterI2M(), new RunningTeamAdapterM2I());
    }
}
