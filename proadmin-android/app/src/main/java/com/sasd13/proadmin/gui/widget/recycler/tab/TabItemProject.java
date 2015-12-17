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
            this.textViewCode.setText(this.code);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setTitle(CharSequence title) {
        this.title = title;

        try {
            this.textViewTitle.setText(this.title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setDescription(CharSequence description) {
        this.description = description;

        try {
            this.textViewDescription.setText(this.description);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        this.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            getView().setOnClickListener(new View.OnClickListener() {

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
        this.textViewCode = (TextView) getView().findViewById(R.id.tabitemproject_textview_code);
        this.textViewTitle = (TextView) getView().findViewById(R.id.tabitemproject_textview_title);
        this.textViewDescription = (TextView) getView().findViewById(R.id.tabitemproject_textview_description);
    }

    private void bindViews() {
        setCode(this.code);
        setTitle(this.title);
        setDescription(this.description);
        setIntent(this.intent);
    }

    private void setOnTouchListener() {
        int color = getView().getContext().getResources().getColor(R.color.background_material_light);
        getView().setOnTouchListener(new ColorOnTouchListener(color));
    }
}
