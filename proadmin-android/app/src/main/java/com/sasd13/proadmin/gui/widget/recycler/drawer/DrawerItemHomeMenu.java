package com.sasd13.proadmin.gui.widget.recycler.drawer;

import android.view.View;
import android.view.ViewStub;

import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItemIntentable;
import com.sasd13.proadmin.R;

public class DrawerItemHomeMenu extends DrawerItemIntentable {

    private int color;
    private View viewColor;

    public DrawerItemHomeMenu() {
        super(R.layout.draweritemhomemenu);
    }

    public void setColor(int color) {
        this.color = color;

        try {
            viewColor.setBackgroundColor(color);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        findViews();
        bindViews();
    }

    private void findViews() {
        viewColor = view.findViewById(R.id.draweritemhomemenu_view_color);
    }

    private void bindViews() {
        if (color == 0) {
            color = view.getContext().getResources().getColor(R.color.customGreenApp);
        }
        setColor(color);
    }
}
