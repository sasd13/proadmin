package com.sasd13.proadmin.gui.widget.recycler.tab;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorOnTouchListener;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.proadmin.R;

public class TabItemProject extends RecyclerItem {

    private CharSequence code, title, description;
    private Intent intent;

    private TextView textViewCode, textViewTitle, textViewDescription;

    public TabItemProject() {
        super(R.layout.tabitemproject);
    }

    public void setCode(CharSequence code) {
        this.code = code;

        try {
            textViewCode.setText(code);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        try {
            textViewTitle.setText(title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setDescription(CharSequence description) {
        this.description = description;

        try {
            textViewDescription.setText(description);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(TabItemProject.this.intent);
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        findViews();
        bindViews();
        setOnTouchListener();
    }

    private void findViews() {
        textViewCode = (TextView) view.findViewById(R.id.tabitemproject_textview_code);
        textViewTitle = (TextView) view.findViewById(R.id.tabitemproject_textview_title);
        textViewDescription = (TextView) view.findViewById(R.id.tabitemproject_textview_description);
    }

    private void bindViews() {
        setCode(code);
        setTitle(title);
        setDescription(description);
        setIntent(intent);
    }

    private void setOnTouchListener() {
        int color = view.getContext().getResources().getColor(R.color.background_material_light);
        view.setOnTouchListener(new ColorOnTouchListener(color));
    }
}
