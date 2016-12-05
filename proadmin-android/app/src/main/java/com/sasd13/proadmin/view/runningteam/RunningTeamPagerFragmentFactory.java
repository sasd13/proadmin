package com.sasd13.proadmin.view.runningteam;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.RunningTeamDependencyWrapper;
import com.sasd13.proadmin.view.IRunningTeamController;

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
    private RunningTeamDetailsFragmentInfos fragmentInfos;
    private RunningTeamDetailsFragmentReports fragmentReports;

    public RunningTeamPagerFragmentFactory(FragmentManager fragmentManager, IRunningTeamController controller, Context context, RunningTeam runningTeam) {
        super(fragmentManager);

        this.controller = controller;
        this.context = context;
        this.runningTeam = runningTeam;
    }

    public void setDependencyWrapper(RunningTeamDependencyWrapper dependencyWrapper) {
        if (fragmentInfos != null && !fragmentInfos.isDetached()) {
            fragmentInfos.setDependencyWrapper(dependencyWrapper);
        }
    }

    public void setReports(List<Report> reports) {
        if (fragmentReports != null && !fragmentReports.isDetached()) {
            fragmentReports.setReports(reports);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = fragmentInfos = RunningTeamDetailsFragmentInfos.newInstance(controller, runningTeam);
                break;
            case 1:
                fragment = fragmentReports = RunningTeamDetailsFragmentReports.newInstance(controller, runningTeam);
                break;
        }

        return fragment;
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
