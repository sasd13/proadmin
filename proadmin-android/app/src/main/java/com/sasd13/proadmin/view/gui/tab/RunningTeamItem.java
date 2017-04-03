package com.sasd13.proadmin.view.gui.tab;

import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.R;

import java.util.Observable;

public class RunningTeamItem extends TabItem {

    private TextView textViewTeam;

    public RunningTeamItem() {
        super(R.layout.tabitem_runningteam);
    }

    @Override
    protected void findViews() {
        super.findViews();

        textViewTeam = (TextView) view.findViewById(R.id.tabitem_runningteam_textview_team);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

        setTeam((RunningTeamItemModel) observable);
    }

    private void setTeam(RunningTeamItemModel runningTeamItemModel) {
        if (textViewTeam != null) {
            textViewTeam.setText(runningTeamItemModel.getTeam());
        }
    }
}