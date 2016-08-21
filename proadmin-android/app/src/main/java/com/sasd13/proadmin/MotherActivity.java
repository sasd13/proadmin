package com.sasd13.proadmin;

import android.content.Intent;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItemNav;
import com.sasd13.proadmin.gui.nav.Nav;
import com.sasd13.proadmin.gui.nav.NavItem;

public abstract class MotherActivity extends DrawerActivity {

    @Override
    protected void fillDrawer() {
        Nav nav = Nav.getInstance(this);
        DrawerItemNav drawerItemNav;

        for (final NavItem navItem : nav.getItems()) {
            drawerItemNav = new DrawerItemNav();

            drawerItemNav.setColor(navItem.getColor());
            drawerItemNav.setLabel(navItem.getText());
            drawerItemNav.setOnClickListener(new RecyclerItem.OnClickListener() {
                @Override
                public void onClickOnRecyclerItem(RecyclerItem recyclerItem) {
                    navItem.getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(navItem.getIntent());
                }
            });

            drawer.addItem(drawerItemNav);
        }
    }
}
