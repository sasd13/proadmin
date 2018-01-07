package com.sasd13.proadmin.android.gui.tab;

import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.android.R;

import java.util.Observable;

public class RunningItem extends TabItem {

    private TextView textViewProject;

    public RunningItem() {
        super(R.layout.tabitem_running);
    }

    @Override
    protected void findViews() {
        super.findViews();

        textViewProject = (TextView) view.findViewById(R.id.tabitem_running_textview_project);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

        setProject((RunningItemModel) observable);
    }

    private void setProject(RunningItemModel runningItemModel) {
        if (textViewProject != null) {
            textViewProject.setText(runningItemModel.getProject());
        }
    }
}