package com.sasd13.proadmin.android.view.gui.tab;

import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.android.R;

import java.util.Observable;

public class ReportItem extends TabItem {

    private TextView textViewDateMeeting, textViewSession;

    public ReportItem() {
        super(R.layout.tabitem_report);
    }

    @Override
    protected void findViews() {
        super.findViews();

        textViewDateMeeting = (TextView) view.findViewById(R.id.tabitem_report_textview_datemeeting);
        textViewSession = (TextView) view.findViewById(R.id.tabitem_report_textview_session);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

        setDateMeeting((ReportItemModel) observable);
        setSession((ReportItemModel) observable);
    }

    private void setDateMeeting(ReportItemModel reportItemModel) {
        if (textViewDateMeeting != null) {
            textViewDateMeeting.setText(reportItemModel.getDateMeeting());
        }
    }

    private void setSession(ReportItemModel reportItemModel) {
        if (textViewSession != null) {
            textViewSession.setText(reportItemModel.getSession());
        }
    }
}
