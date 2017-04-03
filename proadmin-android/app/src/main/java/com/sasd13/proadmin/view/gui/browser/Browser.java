package com.sasd13.proadmin.view.gui.browser;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.controller.ILogOutController;
import com.sasd13.proadmin.controller.IProjectController;
import com.sasd13.proadmin.controller.IReportController;
import com.sasd13.proadmin.controller.IRunningTeamController;
import com.sasd13.proadmin.controller.ISettingsController;
import com.sasd13.proadmin.controller.ITeamController;

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
                    context.getResources().getString(R.string.title_projects),
                    ContextCompat.getDrawable(context, R.drawable.ic_project_small),
                    ContextCompat.getColor(context, R.color.orange),
                    IProjectController.class
            ));
            navItems.add(new BrowserItemModel(
                    EnumBrowserItemType.TEAMS,
                    context.getResources().getString(R.string.title_teams),
                    ContextCompat.getDrawable(context, R.drawable.ic_team_small),
                    ContextCompat.getColor(context, R.color.green),
                    ITeamController.class
            ));
            navItems.add(new BrowserItemModel(
                    EnumBrowserItemType.RUNNINGTEAMS,
                    context.getResources().getString(R.string.title_runningteams),
                    ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp),
                    ContextCompat.getColor(context, R.color.purple),
                    IRunningTeamController.class
            ));
            navItems.add(new BrowserItemModel(
                    EnumBrowserItemType.REPORTS,
                    context.getResources().getString(R.string.title_reports),
                    ContextCompat.getDrawable(context, R.drawable.ic_report_small),
                    ContextCompat.getColor(context, R.color.blue),
                    IReportController.class
            ));
        }

        return navItems;
    }

    public List<BrowserItemModel> getAccountItems(final Context context) {
        if (accountItems.isEmpty()) {
            accountItems.add(new BrowserItemModel(
                    EnumBrowserItemType.SETTINGS,
                    context.getResources().getString(R.string.title_settings),
                    ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp),
                    ContextCompat.getColor(context, R.color.brown),
                    ISettingsController.class
            ));
            accountItems.add(new BrowserItemModel(
                    EnumBrowserItemType.LOGOUT,
                    context.getResources().getString(R.string.drawer_label_logout),
                    ContextCompat.getDrawable(context, R.drawable.ic_exit_to_app_black_24dp),
                    ContextCompat.getColor(context, R.color.greyBackground),
                    ILogOutController.class
            ));
        }

        return accountItems;
    }
}
