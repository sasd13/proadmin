package com.sasd13.proadmin;

import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.drawer.EnumDrawerItemType;
import com.sasd13.proadmin.gui.widget.recycler.browser.Browser;
import com.sasd13.proadmin.gui.widget.recycler.browser.BrowserItemModel;
import com.sasd13.proadmin.util.SessionHelper;

public abstract class MotherActivity extends DrawerActivity {

    @Override
    protected RecyclerHolder getDrawerHolder() {
        RecyclerHolder recyclerHolder = new RecyclerHolder();

        addBrowserItems(recyclerHolder);
        addAccountItems(recyclerHolder);

        return recyclerHolder;
    }

    private void addBrowserItems(RecyclerHolder recyclerHolder) {
        String menu = getResources().getString(R.string.drawer_header_menu);
        Browser browser = Browser.getInstance();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browser.getItems(this)) {
            browserItemModel.setItemType(EnumDrawerItemType.NAV);

            pair = new RecyclerHolderPair(browserItemModel);
            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    startActivity(new Intent(MotherActivity.this, browserItemModel.getTarget()));
                }
            });

            recyclerHolder.add(menu, pair);
        }
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        final BrowserItemModel browserItemModel = new BrowserItemModel(
                getResources().getString(R.string.drawer_label_logout),
                null,
                ContextCompat.getColor(this, R.color.greyBackground),
                HomeActivity.class
        );
        browserItemModel.setItemType(EnumDrawerItemType.DRAWER);

        RecyclerHolderPair pair = new RecyclerHolderPair(browserItemModel);
        pair.addController(EnumActionEvent.CLICK, new IAction() {
            @Override
            public void execute() {
                SessionHelper.logOut(MotherActivity.this);
            }
        });

        recyclerHolder.add(getResources().getString(R.string.drawer_header_account), pair);
    }
}
