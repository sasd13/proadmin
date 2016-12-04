package com.sasd13.proadmin.gui.browser;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.controller.project.ProjectController;
import com.sasd13.proadmin.controller.report.ReportController;
import com.sasd13.proadmin.controller.runningteam.RunningTeamController;
import com.sasd13.proadmin.controller.settings.SettingsController;
import com.sasd13.proadmin.controller.team.TeamController;

import java.util.ArrayList;
import java.util.List;

public class Browser {

    private static class BrowserHolder {
        private static final Browser INSTANCE = new Browser();
    }

    private List<BrowserItemModel> navItems, accountItems;

    private Browser() {
        navItems = new ArrayList<>();
        accountItems = new ArrayList<>();
    }

    public static Browser getInstance() {
        return BrowserHolder.INSTANCE;
    }

    public List<BrowserItemModel> getNavItems(final Context context) {
        if (navItems.isEmpty()) {
            navItems.add(new BrowserItemModel(
                    EnumBrowserItemType.PROJECTS,
                    context.getResources().getString(R.string.activity_projects),
                    ContextCompat.getDrawable(context, R.drawable.ic_project_small),
                    ContextCompat.getColor(context, R.color.orange),
                    ProjectController.class
            ));
            navItems.add(new BrowserItemModel(
                    EnumBrowserItemType.TEAMS,
                    context.getResources().getString(R.string.activity_teams),
                    ContextCompat.getDrawable(context, R.drawable.ic_team_small),
                    ContextCompat.getColor(context, R.color.green),
                    TeamController.class
            ));
            navItems.add(new BrowserItemModel(
                    EnumBrowserItemType.RUNNINGTEAMS,
                    context.getResources().getString(R.string.activity_runningteams),
                    ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp),
                    ContextCompat.getColor(context, R.color.purple),
                    RunningTeamController.class
            ));
            navItems.add(new BrowserItemModel(
                    EnumBrowserItemType.REPORTS,
                    context.getResources().getString(R.string.activity_reports),
                    ContextCompat.getDrawable(context, R.drawable.ic_report_small),
                    ContextCompat.getColor(context, R.color.blue),
                    ReportController.class
            ));
        }

        return navItems;
    }

    public List<BrowserItemModel> getAccountItems(final Context context) {
        if (accountItems.isEmpty()) {
            accountItems.add(new BrowserItemModel(
                    EnumBrowserItemType.SETTINGS,
                    context.getResources().getString(R.string.activity_settings),
                    ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp),
                    ContextCompat.getColor(context, R.color.brown),
                    SettingsController.class
            ));
            accountItems.add(new BrowserItemModel(
                    EnumBrowserItemType.LOGOUT,
                    context.getResources().getString(R.string.drawer_label_logout),
                    ContextCompat.getDrawable(context, R.drawable.ic_exit_to_app_black_24dp),
                    ContextCompat.getColor(context, R.color.greyBackground),
                    HomeActivity.class
            ));
        }

        return accountItems;
    }
}
