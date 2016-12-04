package com.sasd13.proadmin.view.runningteam;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;

import java.util.List;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class RunningTeamPagerFragmentFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_reports};

    private IRunningTeamController controller;
    private Context context;
    private RunningTeam runningTeam;
    private RunningTeamDependencyWrapper dependencyWrapper;
    private List<Report> reports;

    public RunningTeamPagerFragmentFactory(FragmentManager fragmentManager, IRunningTeamController controller, Context context, RunningTeam runningTeam, RunningTeamDependencyWrapper dependencyWrapper, List<Report> reports) {
        super(fragmentManager);

        this.controller = controller;
        this.context = context;
        this.runningTeam = runningTeam;
        this.dependencyWrapper = dependencyWrapper;
        this.reports = reports;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RunningTeamDetailsFragmentInfos.newInstance(controller, runningTeam, dependencyWrapper);
            case 1:
                return RunningTeamDetailsFragmentReports.newInstance(controller, runningTeam, reports);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TITLES[position]);
    }
}
