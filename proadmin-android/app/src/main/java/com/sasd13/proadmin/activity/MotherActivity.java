package com.sasd13.proadmin.activity;

import android.content.Intent;

import com.sasd13.androidex.activity.DrawerActivity;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.gui.browser.Browser;
import com.sasd13.proadmin.gui.browser.BrowserItemModel;
import com.sasd13.proadmin.gui.browser.EnumBrowserItemType;
import com.sasd13.proadmin.util.SessionHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class MotherActivity extends DrawerActivity {

    @Override
    protected RecyclerHolder getDrawerHolder() {
        RecyclerHolder recyclerHolder = new RecyclerHolder();

        addNavItems(recyclerHolder);
        addAccountItems(recyclerHolder);

        return recyclerHolder;
    }

    private void addNavItems(RecyclerHolder recyclerHolder) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getNavItems(this);
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            pair = new RecyclerHolderPair(browserItemModel);
            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    startActivity(new Intent(MotherActivity.this, browserItemModel.getTarget()));
                    finishIfNotInHome();
                }
            });

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_menu), pairs);
    }

    private void finishIfNotInHome() {
        if (!getClass().isAssignableFrom(HomeActivity.class)) {
            finish();
        }
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getAccountItems(this);
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            pair = new RecyclerHolderPair(browserItemModel);

            if (EnumBrowserItemType.LOGOUT.equals(browserItemModel.getBrowserItemType())) {
                pair.addController(EnumActionEvent.CLICK, new IAction() {
                    @Override
                    public void execute() {
                        SessionHelper.logOut(MotherActivity.this);
                    }
                });
            } else {
                pair.addController(EnumActionEvent.CLICK, new IAction() {
                    @Override
                    public void execute() {
                        startActivity(new Intent(MotherActivity.this, browserItemModel.getTarget()));
                        finishIfNotInHome();
                    }
                });
            }

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_account), pairs);
    }
}
