package com.sasd13.proadmin.android.gui.tab;

import android.content.Context;

import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.model.Report;

import java.util.Observable;

public class ReportItemModel extends Observable implements IRecyclerItemModel, ILabelizable {

    private Report report;
    private String labelSession;

    public ReportItemModel(Report report, Context context) {
        this.report = report;
        labelSession = context.getString(R.string.label_session);
    }

    @Override
    public IRecyclerItemType getItemType() {
        return TabItemType.REPORT;
    }

    @Override
    public String getLabel() {
        return report.getNumber();
    }

    public String getSession() {
        return labelSession + " " + String.valueOf(report.getSession());
    }
}
