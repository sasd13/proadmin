package com.sasd13.proadmin.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.sasd13.proadmin.controller.project.ProjectController;
import com.sasd13.proadmin.controller.report.ReportController;
import com.sasd13.proadmin.controller.runningteam.RunningTeamController;
import com.sasd13.proadmin.controller.settings.SettingsController;
import com.sasd13.proadmin.controller.team.TeamController;
import com.sasd13.proadmin.fragment.HomeFragment;
import com.sasd13.proadmin.fragment.IController;
import com.sasd13.proadmin.fragment.IProjectController;
import com.sasd13.proadmin.fragment.IReportController;
import com.sasd13.proadmin.fragment.IRunningController;
import com.sasd13.proadmin.fragment.IRunningTeamController;
import com.sasd13.proadmin.fragment.ISettingsController;
import com.sasd13.proadmin.fragment.IStudentController;
import com.sasd13.proadmin.fragment.ITeamController;
import com.sasd13.proadmin.fragment.ProxyFragment;
import com.sasd13.proadmin.gui.browser.Browser;
import com.sasd13.proadmin.gui.browser.BrowserItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends DrawerActivity {

    private SettingsController settingsController;
    private ProjectController projectController;
    private TeamController teamController;
    private RunningTeamController runningTeamController;
    private ReportController reportController;
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
        settingsController = new SettingsController(this);
        projectController = new ProjectController(this);
        teamController = new TeamController(this);
        runningTeamController = new RunningTeamController(this);
        reportController = new ReportController(this);
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
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getNavItems(this);
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

        recyclerHolder.addAll(getResources().getString(R.string.drawer_header_menu), pairs);
    }

    public IController lookup(Class<? extends IController> mClass) {
        if (ISettingsController.class.isAssignableFrom(mClass)) {
            return settingsController;
        } else if (IProjectController.class.isAssignableFrom(mClass) || IRunningController.class.isAssignableFrom(mClass)) {
            return projectController;
        } else if (ITeamController.class.isAssignableFrom(mClass) || IStudentController.class.isAssignableFrom(mClass)) {
            return teamController;
        } else if (IRunningTeamController.class.isAssignableFrom(mClass)) {
            return runningTeamController;
        } else if (IReportController.class.isAssignableFrom(mClass)) {
            return reportController;
        } else {
            return null;
        }
    }

    private void addAccountItems(RecyclerHolder recyclerHolder) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getAccountItems(this);
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

    public void exit() {
        OptionDialog.showOkCancelDialog(
                this,
                getResources().getString(R.string.button_logout),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logOut();
                    }
                }
        );
    }

    private void logOut() {
        final WaitDialog waitDialog = new WaitDialog(this);
        final Intent intent = new Intent(this, LogInActivity.class);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
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
