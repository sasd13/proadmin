package com.sasd13.proadmin.activity.fragment.runningteam;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerFragmentFactory;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class RunningTeamPagerFragmentFactory implements IPagerFragmentFactory {

    private static final int COUNT = 2;
    private static final @StringRes int[] TITLES = {R.string.title_information, R.string.title_reports};

    private RunningTeam runningTeam;

    public RunningTeamPagerFragmentFactory(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;
    }

    @Override
    public Fragment make(int position) {
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
    public
    @StringRes
    int getPageTitle(int position) {
        return TITLES[position];
    }
}
