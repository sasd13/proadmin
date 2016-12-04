package com.sasd13.proadmin.activity;

import android.content.Intent;
import android.os.Bundle;

import com.sasd13.androidex.activity.DrawerActivity;
import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.gui.browser.Browser;
import com.sasd13.proadmin.gui.browser.BrowserItemModel;
import com.sasd13.proadmin.gui.browser.EnumBrowserItemType;
import com.sasd13.proadmin.util.SessionHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerActivity {

    private Pager pager;

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);

        GUIHelper.colorTitles(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra(Extra.EXIT) && getIntent().getBooleanExtra(Extra.EXIT, false)) {
            getIntent().removeExtra(Extra.EXIT);
            exit();
        }
    }

    private void exit() {
        final WaitDialog waitDialog = new WaitDialog(this);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
                waitDialog.dismiss();
                finish();
            }
        }).start(GUIConstants.TIMEOUT_ACTIVITY);

        waitDialog.show();
    }

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
                    executeBrowserItemAction(browserItemModel);
                }
            });

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_menu), pairs);
    }

    private void executeBrowserItemAction(BrowserItemModel browserItemModel) {
        startActivity(new Intent(MainActivity.this, browserItemModel.getTarget()));
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
                        SessionHelper.logOut(MainActivity.this);
                    }
                });
            } else {
                pair.addController(EnumActionEvent.CLICK, new IAction() {
                    @Override
                    public void execute() {
                        executeBrowserItemAction(browserItemModel);
                    }
                });
            }

            pairs.add(pair);
        }

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_account), pairs);
    }

    @Override
    public void onBackPressed() {
        if (pager == null || !pager.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}
