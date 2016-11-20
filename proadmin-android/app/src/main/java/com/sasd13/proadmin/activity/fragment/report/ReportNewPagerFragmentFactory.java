package com.sasd13.proadmin.activity.fragment.report;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerFragmentFactory;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public class ReportNewPagerFragmentFactory implements IPagerFragmentFactory {

    private static final int COUNT = 2;

    @StringRes
    private static final int[] TITLES = {
            R.string.title_runningteams,
            R.string.title_information,
    };

    private ReportNewFragment parentFragment;
    private RunningTeam runningTeam;

    public ReportNewPagerFragmentFactory(ReportNewFragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    public void setRunningTeam(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;
    }

    @Override
    public Fragment make(int position) {
        switch (position) {
            case 0:
                return ReportNewFragmentRunningTeams.newInstance(parentFragment, runningTeam);
            case 1:
                return ReportNewFragmentInfo.newInstance(runningTeam);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    @StringRes
    public int getPageTitle(int position) {
        return TITLES[position];
    }
}
