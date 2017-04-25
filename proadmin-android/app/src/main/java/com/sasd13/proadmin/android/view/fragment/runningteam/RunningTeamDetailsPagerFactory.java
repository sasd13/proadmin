package com.sasd13.proadmin.android.view.fragment.runningteam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class RunningTeamDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    private final String[] TITLES;

    public RunningTeamDetailsPagerFactory(Fragment fragment) {
        super(fragment.getChildFragmentManager());

        TITLES = new String[]{
                fragment.getString(R.string.title_information),
                fragment.getString(R.string.title_reports)
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RunningTeamDetailsFragmentInfos.newInstance();
            case 1:
                return RunningTeamDetailsFragmentReports.newInstance();
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
