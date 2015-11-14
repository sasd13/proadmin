package proadmin.gui.widget.recycler.drawer;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import flousy.gui.widget.recycler.Recycler;

/**
 * <p>
 * Container class for Navigation drawer :
 * </p>
 * Created by Samir on 23/03/2015.
 */
public class Drawer extends Recycler {

    private DrawerLayout drawerLayout;

    public Drawer(Context context, DrawerLayout drawerLayout) {
        super(context);

        this.drawerLayout = drawerLayout;
    }

    @Override
    public void adapt(RecyclerView recyclerView) {
        super.adapt(recyclerView);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    public boolean isOpened() {
        return this.drawerLayout.isDrawerOpen(getRecyclerView());
    }

    public void setOpened(boolean opened) {
        if (opened) {
            this.drawerLayout.openDrawer(getRecyclerView());
        } else {
            this.drawerLayout.closeDrawer(getRecyclerView());
        }
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }
}
