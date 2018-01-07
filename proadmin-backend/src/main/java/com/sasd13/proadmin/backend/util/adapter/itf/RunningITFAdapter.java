package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.Running;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.RunningAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.RunningAdapterM2I;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningITFAdapter extends ITFAdapter<Running, RunningBean> {

	public RunningITFAdapter() {
		super(new RunningAdapterI2M(), new RunningAdapterM2I());
	}
}
