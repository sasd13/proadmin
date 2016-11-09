package com.sasd13.proadmin.activities.fragments.team;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerFragmentFactory;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class TeamPagerFragmentFactory implements IPagerFragmentFactory {

    private static final int COUNT = 2;
    private static final @StringRes int[] TITLES = {R.string.title_information, R.string.title_teams};

    private RunningTeam runningTeam;

    public TeamPagerFragmentFactory(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;
    }

    @Override
    public Fragment make(int position) {
        switch (position) {
            case 0:
                return TeamDetailsFragmentInfos.newInstance(runningTeam);
            case 1:
                return TeamDetailsFragmentStudents.newInstance(runningTeam.getTeam());
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
