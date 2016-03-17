package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorOnTouchListener;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.proadmin.R;

public class TabItemRunning extends RecyclerItem {

    private CharSequence year;
    private Intent intent;

    private TextView textViewYear;

    public void setYear(CharSequence year) {
        this.year = year;

        if (textViewYear != null) {
        	textViewYear.setText(year);
        }
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void setView(View view) {
        super.setView(view);

        findViews();
        bindViews();
        setOnClickListener();
        setOnTouchListener();
    }

    private void findViews() {
        textViewYear = (TextView) view.findViewById(R.id.tabitem_running_textview_year);
    }

    private void bindViews() {
        setYear(year);
        setIntent(intent);
    }

    private void setOnClickListener() {
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                view.getContext().startActivity(TabItemRunning.this.intent);
            }
        });
    }

    private void setOnTouchListener() {
        int color = ContextCompat.getColor(view.getContext(), R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));
    }
}
