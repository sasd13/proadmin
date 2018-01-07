package com.sasd13.proadmin.android.gui.tab;

import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.android.R;

import java.util.Observable;

public class ReportItem extends TabItem {

    private TextView textViewSession;

    public ReportItem() {
        super(R.layout.tabitem_report);
    }

    @Override
    protected void findViews() {
        super.findViews();

        textViewSession = (TextView) view.findViewById(R.id.tabitem_report_textview_session);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

        setSession((ReportItemModel) observable);
    }

    private void setSession(ReportItemModel reportItemModel) {
        if (textViewSession != null) {
            textViewSession.setText(reportItemModel.getSession());
        }
    }
}
