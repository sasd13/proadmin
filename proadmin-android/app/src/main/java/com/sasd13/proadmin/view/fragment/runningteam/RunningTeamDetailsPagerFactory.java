package com.sasd13.proadmin.view.fragment.runningteam;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.scope.RunningTeamWrapper;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class RunningTeamDetailsPagerFactory extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {R.string.title_information, R.string.title_reports};

    private RunningTeamWrapper runningTeamWrapper;
    private Context context;

    public RunningTeamDetailsPagerFactory(Fragment fragment, RunningTeamWrapper runningTeamWrapper) {
        super(fragment.getChildFragmentManager());

        this.runningTeamWrapper = runningTeamWrapper;
        context = fragment.getContext();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RunningTeamDetailsFragmentInfos.newInstance(runningTeamWrapper);
            case 1:
                return RunningTeamDetailsFragmentReports.newInstance(runningTeamWrapper);
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
