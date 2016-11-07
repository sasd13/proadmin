package com.sasd13.proadmin.activities;

import android.content.Intent;

import com.sasd13.androidex.DrawerActivity;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.drawer.EnumDrawerItemType;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.gui.browser.Browser;
import com.sasd13.proadmin.gui.browser.BrowserItemModel;
import com.sasd13.proadmin.util.SessionHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class MotherActivity extends DrawerActivity {

    @Override
    protected RecyclerHolder getDrawerHolder() {
        RecyclerHolder recyclerHolder = new RecyclerHolder();

        addBrowserItems(recyclerHolder);
        addAccountItems(recyclerHolder);

        return recyclerHolder;
    }

    private void addBrowserItems(RecyclerHolder recyclerHolder) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getNavItems(this);
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            browserItemModel.setItemType(EnumDrawerItemType.NAV);

            pair = new RecyclerHolderPair(browserItemModel);
            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    startActivity(new Intent(MotherActivity.this, browserItemModel.getTarget()));
                }
            });

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_menu), pairs);
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getAccountItems(this);
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            browserItemModel.setItemType(EnumDrawerItemType.NAV);

            pair = new RecyclerHolderPair(browserItemModel);

            if (browserItemModel.getLabel().equalsIgnoreCase(getResources().getString(R.string.activity_settings))) {
                pair.addController(EnumActionEvent.CLICK, new IAction() {
                    @Override
                    public void execute() {
                        startActivity(new Intent(MotherActivity.this, browserItemModel.getTarget()));
                    }
                });
            } else {
                pair.addController(EnumActionEvent.CLICK, new IAction() {
                    @Override
                    public void execute() {
                        SessionHelper.logOut(MotherActivity.this);
                    }
                });
            }

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_account), pairs);
    }
}
