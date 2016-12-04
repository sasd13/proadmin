package com.sasd13.proadmin.view.runningteam;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class RunningTeamPagerFragmentFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_reports};

    private Context context;
    private RunningTeam runningTeam;

    public RunningTeamPagerFragmentFactory(FragmentManager fragmentManager, Context context, RunningTeam runningTeam) {
        super(fragmentManager);

        this.context = context;
        this.runningTeam = runningTeam;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RunningTeamDetailsFragmentInfos.newInstance(runningTeam);
            case 1:
                return RunningTeamDetailsFragmentReports.newInstance(runningTeam);
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
