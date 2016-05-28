package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorOnTouchListener;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.proadmin.R;

public class TabItemProject extends RecyclerItem {

    private CharSequence code, title, description;
    private Intent intent;

    private TextView textViewCode, textViewTitle, textViewDescription;

    public void setCode(CharSequence code) {
        this.code = code;

        if (textViewCode != null) {
        	textViewCode.setText(code);
        }
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        if (textViewTitle != null) {
        	textViewTitle.setText(title);
        }
    }

    public void setDescription(CharSequence description) {
        this.description = description;

        if (textViewDescription != null) {
        	textViewDescription.setText(description);
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
        setOnClickListener();
        setOnTouchListener();
    }

    private void findItemViews() {
        textViewCode = (TextView) view.findViewById(R.id.tabitem_project_textview_code);
        textViewTitle = (TextView) view.findViewById(R.id.tabitem_project_textview_title);
        textViewDescription = (TextView) view.findViewById(R.id.tabitem_project_textview_description);
    }

    private void bindItemViews() {
        setCode(code);
        setTitle(title);
        setDescription(description);
        setIntent(intent);
    }

    private void setOnClickListener() {
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                view.getContext().startActivity(TabItemProject.this.intent);
            }
        });
    }

    private void setOnTouchListener() {
        int color = ContextCompat.getColor(view.getContext(), R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));
    }
}
