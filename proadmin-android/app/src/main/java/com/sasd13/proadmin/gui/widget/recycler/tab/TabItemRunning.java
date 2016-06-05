package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.R;

public class TabItemRunning extends TabItem {

    private String year;
    private TextView textViewYear;
    private OnClickListener onClickListener;

    public TabItemRunning() {
        super(R.layout.tabitem_running);
    }

    public void setYear(String year) {
        this.year = year;

        if (textViewYear != null) {
        	textViewYear.setText(year);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected void findItemViews() {
        super.findItemViews();

        textViewYear = (TextView) view.findViewById(R.id.tabitem_running_textview_year);
    }

    @Override
    protected void bindItemViews() {
        super.bindItemViews();

        setYear(year);
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClickOnRecyclerItem(TabItemRunning.this);
                }
            }
        });
    }
}
