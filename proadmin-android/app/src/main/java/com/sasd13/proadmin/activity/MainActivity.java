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
import com.sasd13.proadmin.controller.project.ProjectController;
import com.sasd13.proadmin.controller.report.ReportController;
import com.sasd13.proadmin.controller.runningteam.RunningTeamController;
import com.sasd13.proadmin.controller.settings.SettingsController;
import com.sasd13.proadmin.controller.team.TeamController;
import com.sasd13.proadmin.gui.browser.Browser;
import com.sasd13.proadmin.gui.browser.BrowserItemModel;
import com.sasd13.proadmin.view.HomeFragment;
import com.sasd13.proadmin.view.IController;
import com.sasd13.proadmin.view.IProjectController;
import com.sasd13.proadmin.view.IReportController;
import com.sasd13.proadmin.view.IRunningController;
import com.sasd13.proadmin.view.IRunningTeamController;
import com.sasd13.proadmin.view.ISettingsController;
import com.sasd13.proadmin.view.IStudentController;
import com.sasd13.proadmin.view.ITeamController;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerActivity {

    private SettingsController settingsController;
    private ProjectController projectController;
    private TeamController teamController;
    private RunningTeamController runningTeamController;
    private ReportController reportController;

    private Pager pager;

    public void setPager(Pager pager) {
        this.pager = pager;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);
        GUIHelper.colorTitles(this);
        showHome();
        init();
    }

    private void showHome() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, HomeFragment.newInstance())
                .commit();
    }

    private void init() {
        settingsController = new SettingsController(this);
        projectController = new ProjectController(this);
        teamController = new TeamController(this);
        runningTeamController = new RunningTeamController(this);
        reportController = new ReportController(this);
    }

    @Override
    public void onBackPressed() {
        if (pager == null || !pager.handleBackPress(this)) {
            super.onBackPressed();
        }
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

    public void exit() {
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
}
