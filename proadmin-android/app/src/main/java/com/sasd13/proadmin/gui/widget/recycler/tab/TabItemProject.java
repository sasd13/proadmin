package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.TabItem;
import com.sasd13.proadmin.R;

public class TabItemProject extends TabItem {

    private String code, description;
    private TextView textViewCode, textViewDescription;
    private OnClickListener onClickListener;

    public TabItemProject() {
        super(R.layout.tabitem_project);
    }

    public void setCode(String code) {
        this.code = code;

        if (textViewCode != null) {
        	textViewCode.setText(code);
        }
    }

    public void setDescription(String description) {
        this.description = description;

        if (textViewDescription != null) {
        	textViewDescription.setText(description);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected void findItemViews() {
        super.findItemViews();

        textViewCode = (TextView) view.findViewById(R.id.tabitem_project_textview_code);
        textViewDescription = (TextView) view.findViewById(R.id.tabitem_project_textview_description);
    }

    @Override
    protected void bindItemViews() {
        super.bindItemViews();

        setCode(code);
        setDescription(description);
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClickOnRecyclerItem(TabItemProject.this);
                }
            }
        });
    }
}
