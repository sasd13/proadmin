package com.sasd13.proadmin.gui.widget.recycler.drawer;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItem;
import com.sasd13.proadmin.R;

public class DrawerItemHomeMenu extends DrawerItem {

    private int color;
    private View viewColor;

    public void setColor(int color) {
        this.color = color;

        try {
            viewColor.setBackgroundColor(color);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setView(View view) {
        super.setView(view);

        findViews();
        bindViews();
    }

    private void findViews() {
        viewColor = view.findViewById(R.id.draweritem_homemenu_view_color);
    }

    private void bindViews() {
        if (color == 0) {
            color = ContextCompat.getColor(view.getContext(), R.color.customGreenApp);
        }
        setColor(color);
    }
}
