package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.R;

import java.util.Observable;

public class RunningItem extends TabItem {

    private TextView textViewYear;

    public RunningItem() {
        super(R.layout.tabitem_running);
    }

    @Override
    protected void findViews() {
        super.findViews();

        textViewYear = (TextView) view.findViewById(R.id.tabitem_running_textview_year);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

        setYear((RunningItemModel) observable);
    }

    private void setYear(RunningItemModel runningItemModel) {
        if (textViewYear != null) {
            textViewYear.setText(runningItemModel.getYear());
        }
    }
}
