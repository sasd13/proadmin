package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorOnTouchListener;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.proadmin.R;

public class TabItemTeam extends RecyclerItem {

    private CharSequence code;
    private Intent intent;
    private TextView textViewCode;

    public TabItemTeam() {
        super(R.layout.tabitem_team);
    }

    public void setCode(CharSequence code) {
        this.code = code;

        if (textViewCode != null) {
        	textViewCode.setText(code);
        }
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void bindView(View view) {
        super.bindView(view);

        findItemViews();
        bindItemViews();
        setListeners();
    }

    protected void findItemViews() {
        textViewCode = (TextView) view.findViewById(R.id.tabitem_team_textview_code);
    }

    protected void bindItemViews() {
        setCode(code);
        setIntent(intent);
    }

    protected void setListeners() {
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                view.getContext().startActivity(TabItemTeam.this.intent);
            }
        });

        int color = ContextCompat.getColor(view.getContext(), R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));
    }
}
