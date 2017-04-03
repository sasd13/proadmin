package com.sasd13.proadmin.view.gui.tab;

import android.content.Context;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.androidex.util.DateTimeHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;

import java.util.Observable;

public class ReportItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private Report report;
    private String format, labelSession;

    public ReportItemModel(Report report, Context context) {
        this.report = report;
        format = DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.EnumFormat.LONG);
        labelSession = context.getResources().getString(R.string.label_session);
    }

    @Override
    public IRecyclerItemType getItemType() {
        return TabItemType.REPORT;
    }

    @Override
    public String getLabel() {
        return report.getNumber();
    }

    public String getDateMeeting() {
        return DateTimeHelper.format(report.getDateMeeting(), format);
    }

    public String getSession() {
        return labelSession + " " + String.valueOf(report.getSession());
    }
}
