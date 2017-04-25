package com.sasd13.proadmin.android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sasd13.androidex.activity.DrawerActivity;
import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.provider.ControllerProvider;
import com.sasd13.proadmin.android.util.SessionHelper;
import com.sasd13.proadmin.android.view.Browser;
import com.sasd13.proadmin.android.view.IBrowsable;
import com.sasd13.proadmin.android.view.IController;
import com.sasd13.proadmin.android.view.fragment.HomeFragment;
import com.sasd13.proadmin.android.view.gui.browser.BrowserItemModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerActivity {

    private ControllerProvider controllerProvider;
    private IPagerHandler pagerHandler;

    public void setPagerHandler(IPagerHandler pagerHandler) {
        this.pagerHandler = pagerHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controllerProvider = new ControllerProvider();

        setContentView(R.layout.layout_container);
        startHomeFragment();
    }

    private void startHomeFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, HomeFragment.newInstance())
                .commit();
    }

    @Override
    protected RecyclerHolder getDrawerHolder() {
        RecyclerHolder recyclerHolder = new RecyclerHolder();

        addNavItems(recyclerHolder);
        addAccountItems(recyclerHolder);

        return recyclerHolder;
    }

    private void addNavItems(RecyclerHolder recyclerHolder) {
        List<RecyclerHolderPair> pairs = makeItems(Browser.getInstance().getNavItems(this));

        recyclerHolder.addAll(getString(R.string.drawer_header_menu), pairs);
    }

    @NonNull
    private List<RecyclerHolderPair> makeItems(List<BrowserItemModel> browserItemModels) {
        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            pair = new RecyclerHolderPair(browserItemModel);

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    ((IBrowsable) lookup(browserItemModel.getTarget())).browse();
                    setDrawerOpened(false);
                }
            });

            pairs.add(pair);
        }
        return pairs;
    }

    public IController lookup(Class mClass) {
        return controllerProvider.provide(mClass, this);
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        List<RecyclerHolderPair> pairs = makeItems(Browser.getInstance().getAccountItems(this));

        recyclerHolder.addAll(getString(R.string.drawer_header_account), pairs);
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress()) {
            super.onBackPressed();
        }
    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void logOut() {
        OptionDialog.showOkCancelDialog(
                this,
                getString(R.string.button_logout),
                getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        exit();
                    }
                }
        );
    }

    private void exit() {
        final WaitDialog waitDialog = new WaitDialog(this);
        final Intent intent = new Intent(this, IdentityActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                SessionHelper.clear(MainActivity.this);
                startActivity(intent);
                waitDialog.dismiss();
                finish();
            }
        }).start(GUIConstants.TIMEOUT_ACTIVITY);

        waitDialog.show();
    }

    public void clearHistory() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}