package com.sasd13.proadmin.gui.widget.recycler.drawer;

import android.view.View;
import android.view.ViewStub;

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
            this.viewColor.setBackgroundColor(this.color);
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
        this.viewColor = getView().findViewById(R.id.draweritemhomemenu_view_color);
    }

    private void bindViews() {
        if (this.color == 0) {
            this.color = getView().getContext().getResources().getColor(R.color.customGreenApp);
        }
        setColor(this.color);
    }
}
