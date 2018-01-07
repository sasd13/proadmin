package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.ReportAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.ReportAdapterM2I;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class ReportITFAdapter extends ITFAdapter<Report, ReportBean> {

    public ReportITFAdapter() {
        super(new ReportAdapterI2M(), new ReportAdapterM2I());
    }
}
