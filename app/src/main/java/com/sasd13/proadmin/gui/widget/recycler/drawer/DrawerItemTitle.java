package com.sasd13.proadmin.gui.widget.recycler.drawer;

import com.sasd13.proadmin.R;

public class DrawerItemTitle extends DrawerItem {

    public DrawerItemTitle() {
        super(R.layout.draweritemtitle);
    }

    @Override
    public void setText(CharSequence text) {
        super.setText(text.toString().toUpperCase());
    }
}
