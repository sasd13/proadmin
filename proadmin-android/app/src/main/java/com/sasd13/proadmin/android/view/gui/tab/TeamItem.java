package com.sasd13.proadmin.android.view.gui.tab;

import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.R;

import java.util.Observable;

public class TeamItem extends TabItem {

    private TextView textViewName;

    public TeamItem() {
        super(R.layout.tabitem_team);
    }

    @Override
    protected void findViews() {
        super.findViews();

        textViewName = (TextView) view.findViewById(R.id.tabitem_team_textview_name);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);

        setName((TeamItemModel) observable);
    }

    private void setName(TeamItemModel teamItemModel) {
        if (textViewName != null) {
            textViewName.setText(teamItemModel.getName());
        }
    }
}
