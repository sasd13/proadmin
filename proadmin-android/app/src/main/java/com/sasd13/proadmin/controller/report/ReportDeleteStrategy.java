package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.service.IReportService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportDeleteStrategy extends RequestorStrategy {

    private ReportController controller;
    private IReportService service;

    public ReportDeleteStrategy(ReportController controller, IReportService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        service.delete((Report[]) in);

        return null;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.display(R.string.message_deleted);
        controller.entry();
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
