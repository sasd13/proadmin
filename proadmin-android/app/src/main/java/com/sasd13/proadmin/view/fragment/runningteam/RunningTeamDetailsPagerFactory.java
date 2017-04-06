package com.sasd13.proadmin.view.fragment.runningteam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.scope.RunningTeamScope;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class RunningTeamDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    private final String[] TITLES;

    private RunningTeamScope runningTeamScope;

    public RunningTeamDetailsPagerFactory(Fragment fragment, RunningTeamScope runningTeamScope) {
        super(fragment.getChildFragmentManager());

        this.runningTeamScope = runningTeamScope;

        TITLES = new String[]{
                fragment.getString(R.string.title_information),
                fragment.getString(R.string.title_reports)
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RunningTeamDetailsFragmentInfos.newInstance(runningTeamScope);
            case 1:
                return RunningTeamDetailsFragmentReports.newInstance(runningTeamScope);
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
        return TITLES[position];
    }
}
