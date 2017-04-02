package com.sasd13.proadmin.activity;

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
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.controller.LogOutController;
import com.sasd13.proadmin.controller.project.ProjectController;
import com.sasd13.proadmin.controller.report.ReportController;
import com.sasd13.proadmin.controller.runningteam.RunningTeamController;
import com.sasd13.proadmin.controller.settings.SettingsController;
import com.sasd13.proadmin.controller.team.TeamController;
import com.sasd13.proadmin.fragment.HomeFragment;
import com.sasd13.proadmin.controller.IController;
import com.sasd13.proadmin.controller.ILogOutController;
import com.sasd13.proadmin.controller.IProjectController;
import com.sasd13.proadmin.controller.IReportController;
import com.sasd13.proadmin.controller.IRunningController;
import com.sasd13.proadmin.controller.IRunningTeamController;
import com.sasd13.proadmin.controller.ISettingsController;
import com.sasd13.proadmin.controller.IStudentController;
import com.sasd13.proadmin.controller.ITeamController;
import com.sasd13.proadmin.fragment.ProxyFragment;
import com.sasd13.proadmin.gui.browser.Browser;
import com.sasd13.proadmin.gui.browser.BrowserItemModel;
import com.sasd13.proadmin.util.SessionHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MainActivity extends DrawerActivity {

    private Map<Class<? extends IController>, IController> router;
    private IPagerHandler pagerHandler;
    private Stack<Fragment> stack = new Stack<>();

    public void setPagerHandler(IPagerHandler pagerHandler) {
        this.pagerHandler = pagerHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setContentView(R.layout.layout_container);
        showHome();
    }

    private void init() {
        router = new HashMap<>();

        router.put(ISettingsController.class, new SettingsController(this));
        router.put(IRunningTeamController.class, new RunningTeamController(this));
        router.put(IReportController.class, new ReportController(this));
        router.put(ILogOutController.class, new LogOutController(this));

        IProjectController projectController = new ProjectController(this);
        router.put(IProjectController.class, projectController);
        router.put(IRunningController.class, projectController);

        ITeamController teamController = new TeamController(this);
        router.put(ITeamController.class, teamController);
        router.put(IStudentController.class, teamController);
    }

    private void showHome() {
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

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_menu), pairs);
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
                    lookup(browserItemModel.getTarget()).entry();
                    setDrawerOpened(false);
                }
            });

            pairs.add(pair);
        }
        return pairs;
    }

    public IController lookup(Class<? extends IController> mClass) {
        return router.get(mClass);
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        List<RecyclerHolderPair> pairs = makeItems(Browser.getInstance().getAccountItems(this));

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_account), pairs);
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress()) {
            super.onBackPressed();

            if (!stack.isEmpty()) {
                stack.pop();

                if (!stack.isEmpty() && ProxyFragment.class.isAssignableFrom(stack.peek().getClass())) {
                    onBackPressed();
                }
            }
        }
    }

    public void startFragment(Fragment fragment) {
        stack.push(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void logOut() {
        OptionDialog.showOkCancelDialog(
                this,
                getResources().getString(R.string.button_logout),
                getResources().getString(R.string.message_confirm),
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
        final Intent intent = new Intent(this, LogInActivity.class);

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
        if (!stack.isEmpty()) {
            stack.clear();
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
