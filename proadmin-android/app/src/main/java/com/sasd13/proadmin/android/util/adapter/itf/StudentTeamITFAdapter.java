package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.StudentTeamAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.StudentTeamAdapterM2I;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class StudentTeamITFAdapter extends ITFAdapter<StudentTeam, StudentTeamBean> {

    public StudentTeamITFAdapter() {
        super(new StudentTeamAdapterI2M(), new StudentTeamAdapterM2I());
    }
}
