package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.view.View;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.R;

public class TabItemTeam extends TabItem {

    private OnClickListener onClickListener;

    public TabItemTeam() {
        super(R.layout.tabitem_team);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected void setListeners() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClickOnRecyclerItem(TabItemTeam.this);
                }
            }
        });
    }
}
