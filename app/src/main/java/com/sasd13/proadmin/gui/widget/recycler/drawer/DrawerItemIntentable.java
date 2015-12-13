package com.sasd13.proadmin.gui.widget.recycler.drawer;

import android.content.Intent;
import android.view.View;
import android.view.ViewStub;

import com.sasd13.proadmin.R;

import com.sasd13.proadmin.gui.color.ColorOnTouchListener;

public class DrawerItemIntentable extends DrawerItem {

    private Intent intent;

    public DrawerItemIntentable() {
        super();
    }

    public DrawerItemIntentable(int layoutResource) {
        super(layoutResource);
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        this.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        setOnClickListener();
        setOnTouchListener();
    }

    private void setOnClickListener() {
        getView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    view.getContext().startActivity(intent);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setOnTouchListener() {
        int color = getView().getContext().getResources().getColor(R.color.background_material_light);
        getView().setOnTouchListener(new ColorOnTouchListener(color));
    }
}
