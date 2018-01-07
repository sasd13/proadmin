package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.RunningAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.RunningAdapterM2I;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class RunningITFAdapter extends ITFAdapter<Running, RunningBean> {

    public RunningITFAdapter() {
        super(new RunningAdapterI2M(), new RunningAdapterM2I());
    }
}
